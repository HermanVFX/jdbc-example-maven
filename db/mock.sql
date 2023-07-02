CREATE TABLE TABLE_LIST
(TABLE_NAME VARCHAR(32) COMMENT 'имя таблицы',
 PK VARCHAR(256) COMMENT 'поля первичного ключа, разделитель - запятая');

CREATE TABLE TABLE_COLS
(TABLE_NAME VARCHAR(32) COMMENT 'имя таблицы',
 COLUMN_NAME VARCHAR(32) COMMENT 'имя поля',
 COLUMN_TYPE VARCHAR(32) COMMENT 'тип данных поля - INT или VARCHAR');

select * from TABLE_LIST;
select * from TABLE_COLS;

select
    TABLE_NAME,
    (select PK from TABLE_LIST
               where LOWER(TABLE_LIST.TABLE_NAME) = LOWER(TABLE_COLS.TABLE_NAME)
                 and LOWER(PK) = LOWER(COLUMN_NAME)) as COLUMN_NAME,
    COLUMN_TYPE
from TABLE_COLS;

select
    TABLE_NAME,
    COLUMN_NAME,
    COLUMN_TYPE
from TABLE_COLS
    where lower(COLUMN_NAME) in (select lower(PK) from TABLE_LIST);
