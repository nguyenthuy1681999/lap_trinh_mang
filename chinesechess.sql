CREATE DATABASE co_tuong;
USE co_tuong;
CREATE TABLE tbl_nguoichoi(
id INT(3) AUTO_INCREMENT NOT NULL,
ten VARCHAR(20) NOT NULL,
mat_khau VARCHAR(20) NOT NULL,
so_tran_thang INT(4),
so_tran_thua INT(4),
so_tran_hoa INT(4),
nuoc_di_thang INT(6),
nuoc_di_thua INT(6),
diem FLOAT(6),
CONSTRAINT pk_nguoichoi PRIMARY KEY(id)
)
SELECT * FROM co_tuong.tbl_nguoichoi;
