create trigger done_report
On [dbo].[Report]
after insert
As
Begin
	declare @request_id int
	select @request_id = request_id from inserted
	UPDATE King_Request
		SET isDone = 1
		WHERE id = @request_id
end

select [transaction_type],[region_id], sum([amount])
from [dbo].[Financial_Transactions]
where [region_id] = 2
group by [transaction_type], [region_id]

select sum([amount]) from [dbo].[Financial_Transactions]
			where [region_id] = 1 and [transaction_type] like 'income'

create function calBudget (@region_id int) returns decimal(20,2)
as begin
	declare @rs decimal(20,2),
			@income decimal(20,2),
			@outcome decimal(20,2)
	select @income = sum([amount]) from [dbo].[Financial_Transactions]
			where [region_id] = @region_id and [transaction_type] like 'income'
	select @outcome = sum([amount]) from [dbo].[Financial_Transactions]
			where [region_id] = @region_id and [transaction_type] like 'outcome'
	if (@income is null)
	begin
		select @rs = -@outcome
	end
	else if  (@outcome is null)
	begin
		select @rs = @income
	end
	else
	begin
		select @rs = @income - @outcome
	end
	return @rs
end

create function budgetInTime (@region_id int, @year1 int, @year2 int, @month1 int, @month2 int, @type nvarchar(10)) 
	returns decimal(20,2)
as begin
	select @income = sum([amount]) from [dbo].[Financial_Transactions]
			where [region_id] = @region_id and [transaction_type] like @type
			and year([transaction_date]) >= @year1 
			and year([transaction_date]) <= @year2
			and month([transaction_date]) >= @month1 
			and month([transaction_date]) <= @month2  
	returns @income
end

CREATE FUNCTION budgetInTime (@region_id INT, @year1 INT, @year2 INT, @month1 INT, @month2 INT, @type NVARCHAR(10)) 
RETURNS DECIMAL(20, 2)
AS 
BEGIN
    DECLARE @income DECIMAL(20, 2)

    SELECT @income = SUM([amount])
    FROM [dbo].[Financial_Transactions]
    WHERE [region_id] = @region_id 
        AND [transaction_type] = @type
        AND [transaction_date] BETWEEN DATEFROMPARTS(@year1, @month1, 1) AND EOMONTH(DATEFROMPARTS(@year2, @month2, 1))

    RETURN ISNULL(@income, 0)
END

select dbo.budgetInTime(1,2019,2023,1,12,'outcome')


select sum([amount]) from [dbo].[Financial_Transactions]
			where [region_id] = 1 and [transaction_type] like 'outcome'
			and year([transaction_date]) >= 2019
			and year([transaction_date]) <= 2023
			and month([transaction_date]) >= 1
			and month([transaction_date]) <= 12

create function calBudget2 (@region_id int, @year1 int, @year2 int, 
							@month1 int, @month2 int)
returns decimal(20,2)
as begin
	declare @rs decimal(20,2),
			@income decimal(20,2),
			@outcome decimal(20,2)
	select @income = dbo.budgetInTime(@region_id, @year1, @year2, @month1, @month2, 'income')
	select @outcome = dbo.budgetInTime(@region_id, @year1, @year2, @month1, @month2, 'outcome')
	if (@income is null)
		select @income = 0
	if  (@outcome is null)
		select @outcome = 0
	select @rs = @income - @outcome
	return @rs
end
select dbo.calBudget2(1, 2022, 2023, 1, 12) as result