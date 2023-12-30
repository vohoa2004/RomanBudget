
-- Tạo cơ sở dữ liệu
CREATE DATABASE BudgetDB3;

-- Sử dụng cơ sở dữ liệu mới tạo
USE BudgetDB3;

-- Tạo bảng User
CREATE TABLE [User] (
    id INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50),
    [password] NVARCHAR(50),
    user_type NVARCHAR(20)
);


-- Tạo bảng Consul
CREATE TABLE Consul (
    id INT PRIMARY KEY,
    name NVARCHAR(50),
    age INT,
    address NVARCHAR(100),
    term_count INT,
    salary DECIMAL(10, 2),
    noble_status bit,
);

alter table Consul
add constraint FK_ID foreign key (id) references [User](id)

ALTER TABLE CONSUL
Add image nvarchar(200)

-- Tạo bảng Region
CREATE TABLE Region (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(50)
);

ALTER TABLE Region
ADD image nvarchar(200)

-- muon doi region_consul thi phai xet coi nam hien tai co nam giua start_year va end_year cua dua nao khong
-- neu co thi khong doi duoc, khong thi moi doi duoc
CREATE TABLE Region_Consul(
	region_id int references Region(id),
	consul_id int references Consul(id),
	start_year int,
	end_year as (start_year + 3),
	status bit,
	primary key (region_id, consul_id, start_year)
)


-- Tạo bảng Versaille
CREATE TABLE Versaille (
    id INT PRIMARY KEY IDENTITY(1,1),
    region_id INT,
    name NVARCHAR(50),
	FOREIGN KEY (region_id) REFERENCES Region(id)
    );
	alter table Versaille
	add image nvarchar(200)

CREATE TABLE Versaille_Situation (
	versaille_id int references Versaille(id),
	[quarter] int check ([quarter] between 1 and 4),
	[year] int check([year] > 0),
	market_value DECIMAL(18, 2),
    growth_rate DECIMAL(5, 2),
    revolt_rate DECIMAL(5, 2),
    food_supply INT CHECK (food_supply BETWEEN 1 AND 10),
    primary key (versaille_id, [quarter], [year])
)

-- Tạo bảng Financial_Transactions
CREATE TABLE Financial_Transactions (
    id INT PRIMARY KEY identity(1,1),
    region_id INT,
    transaction_type NVARCHAR(10) CHECK (transaction_type IN ('income', 'outcome')),
    amount DECIMAL(10, 2),
    [description] NVARCHAR(255),
    transaction_date DATE,
    FOREIGN KEY (region_id) REFERENCES Region(id)
);
ALTER TABLE Financial_Transactions
ALTER column transaction_date datetime

ALTER TABLE Financial_Transactions
ALTER column amount DECIMAL(20, 2)

-- Tax category
Create table Tax_Category (
	id int PRIMARY KEY,
	name nvarchar(200),
	unit_price DECIMAL(10, 2) --per year
)

-- Tạo bảng Tax
CREATE TABLE Tax (
    id INT PRIMARY KEY IDENTITY(1,1),
    region_id INT,
    tax_amount DECIMAL(10, 2), -- = unit_price * unit * 1 year
    tribute_amount DECIMAL(10, 2),
    FOREIGN KEY (region_id) REFERENCES Region(id)
);

ALTER TABLE Tax
ADD tax_category_id int references Tax_Category(id)

ALTER TABLE Tax
ADD deadline DATE;

ALTER TABLE Tax
ADD [year] int;

ALTER TABLE Tax
ADD paid_date DATE;

-- Tạo bảng King_Request
CREATE TABLE King_Request (
    id INT IDENTITY(1,1) PRIMARY KEY,
    region_id INT,
    request_date DATETIME,
    request_content NVARCHAR(255),
	deadline DATETIME,
	isDone bit default 0,
	FOREIGN KEY (region_id) REFERENCES Region(id)
);
use BudgetDB3
select k.[request_content], r.* 
from [dbo].[Report] r join [dbo].[King_Request] k on r.request_id = k.id

-- Tạo bảng Report
CREATE TABLE Report (
    report_id INT PRIMARY KEY IDENTITY(1,1),
    request_id INT,
    written_date DATE,
    delivered_date DATETIME,
    location NVARCHAR(50),
    report_data NVARCHAR(MAX),
    FOREIGN KEY (request_id) REFERENCES King_Request(id),
);
ALTER TABLE Report
ALTER column delivered_date datetime

ALTER TABLE Report
Add report_title nvarchar(200) default 'No title'

ALTER TABLE Report
Add report_type bit 

ALTER TABLE Report
Add writer_id int

-- viết function tính toán budget của 1 versaille, 1 region
-- Viết trigger khi nộp báo cáo có request id mà chưa solve thì sẽ set isDone bên request đó từ 0 thành 1
select * from [dbo].[Financial_Transactions]

-- Them field tinh trang cua bao cao (dang duoc xem/ duyet roi/ tu choi) va comment vao bang report
-- ban dau mac dinh la dang xem xet
-- king la nguoi danh gia va comment 
-- neu king chon la ok roi thi ko sao
-- neu king chon la tu choi thi chuyen king toi cho ra request moi va tu dong dien form ra request do bang region va noi dung request ban dau cua report do

alter table [dbo].[Report] 
add status  nvarchar(50) default 'pending' check(status in ('pending', 'approved', 'rejected')) 

select *
from Report

alter table [dbo].[Report]
add comment nvarchar(max)

