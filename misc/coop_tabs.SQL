

create user ALCDBA identified by ALC;
grant all privileges, DBA to ALCDBA;


login as ALCDBA:

create tablespace ALCSDA01
datafile 'C:\Oracle\Database\oradata\AMKENTDB\ALC_SDA1.DBF' size 500M online;


--*********************************************
--drop table PARAM cascade constraints;
create table PARAM
(maincompany    varchar2(80),
 mainaddress    varchar2(80),
 dbname         varchar2(20)
)
tablespace ALCSDA01;


--*********************************************
--drop table DBUSER cascade constraints;
create table DBUSER
(username       varchar2(20),
 password       varchar2(20)
)
tablespace ALCSDA01;


--*********************************************
--drop table COMPANY cascade constraints;
create table COMPANY
(compid         number(10)   not null,
 compname       varchar2(30) not null,
 address1       varchar2(30),
 address2       varchar2(30),
 address3       varchar2(30),
 contactperson  varchar2(30),
 contactno1     varchar2(15),
 contactno2     varchar2(15),
 email          varchar2(30),
 compvname      varchar2(30),
 insby          varchar2(15),
 insdt          date,
 updby          varchar2(15),
 upddt          date,
 constraint COMPANY_CONS1 primary key (compid)
            using index tablespace ALCSDA01)
tablespace ALCSDA01;

create unique index COMPANY_IDX1 on COMPANY(compvname) tablespace ALCSDA01;

--*********************************************
--drop table MEMBER cascade constraints;
create table MEMBER
(memberid      number(10)   not null,
 firstname     varchar2(20),
 midinit       varchar2(3),
 lastname      varchar2(20),
 address1      varchar2(30),
 address2      varchar2(30),
 address3      varchar2(30),
 contactno1    varchar2(15),
 contactno2    varchar2(15),
 email         varchar2(45),
 compid        number(10),
 contribution  number(9,2),
 contribtotal  number(9,2),
 maxshare      number(9,2),
 status        varchar2(1)  default 'E',
 hiredt        date,
 resigndt      date,
 remarks       varchar2(200),
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint MEMBER_CONS1 primary key (memberid)
            using index tablespace ALCSDA01)
tablespace ALCSDA01;


--*********************************************
--drop table NONMEMBER cascade constraints;
create table NONMEMBER
(memberid      number(10)   not null,
 firstname     varchar2(20),
 midinit       varchar2(3),
 lastname      varchar2(20),
 address1      varchar2(30),
 address2      varchar2(30),
 address3      varchar2(30),
 contactno1    varchar2(15),
 contactno2    varchar2(15),
 email         varchar2(45),
 compid        number(10),
 remarks       varchar2(200),
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint NONMEMBER_CONS1 primary key (memberid)
            using index tablespace ALCSDA01)
tablespace ALCSDA01;


--*********************************************
--drop table SUPPLIER cascade constraints;
create table SUPPLIER
(suppid         number(10)   not null,
 suppname       varchar2(30) not null,
 address1       varchar2(30),
 address2       varchar2(30),
 address3       varchar2(30),
 contactperson  varchar2(30),
 contactno1     varchar2(15),
 contactno2     varchar2(15),
 email          varchar2(30),
 balance        number(9,2),
 suppvname      varchar2(30),
 insby          varchar2(15),
 insdt          date,
 updby          varchar2(15),
 upddt          date,
 constraint SUPPLIER_CONS1 primary key (suppid)
            using index tablespace ALCSDA01)
tablespace ALCSDA01;

create unique index SUPPLIER_IDX1 on SUPPLIER(suppvname) tablespace ALCSDA01;



--*********************************************
--drop table LOAN_HDR cascade constraints;
create table LOAN_HDR
(loanid        number(10)  not null,
 memberid      number(10)  not null,
 loantype      varchar2(1) default 'R',
 refno         varchar2(10),                                     *** new ***
 grantdt       date,
 startdt       date,
 enddt         date,
 loanamt       number(9,2),
 montopay      number(3),
 interestrt    number(5,2),
 interestamt   number(9,2),
 payableamt    number(9,2),
 balance       number(9,2),
 prevbalamt    number(9,2),
 releasedamt   number(9,2),
 amortization  number(9,2),
 checkno       varchar2(15),
 status        varchar2(1) default 'A',
 remarks       varchar2(200), 
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint LOAN_HDR_CONS1 primary key (loanid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

create unique index LOAN_HDR_IDX1 on LOAN_HDR(memberid,loantype,grantdt) tablespace PSTTDA01;

--*********************************************
--drop table LOAN_DTL cascade constraints;
create table LOAN_DTL
(loandtlid         number(10)  not null,
 loanid            number(10)  not null,
 amordate          date,
 mon_amort         number(9,2),
 mon_premium       number(9,2),
 mon_interest      number(9,2),
 mon_penalty       number(9,2),
 mon_premium_bal   number(9,2),
 mon_interest_bal  number(9,2),
 mon_penalty_bal   number(9,2),
 insby             varchar2(15),
 insdt             date,
 updby             varchar2(15),
 upddt             date,
 constraint LOAN_DTL_CONS1 primary key (loandtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

create index LOAN_DTL_IDX1 on LOAN_DTL(loanid) tablespace PSTTDA01;

--*********************************************
--drop table CASHLOAN cascade constraints;
create table CASHLOAN
(loanid        number(10)  not null,
 memberid      number(10)  not null,
 grantdt       date,
 billingdt     date,
 loanamt       number(9,2),
 interestrt    number(5,2),
 interestamt   number(9,2),
 balance       number(9,2),
 releasedamt   number(9,2),
 checkno       varchar2(15),
 status        varchar2(1)  default 'A',
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint CASHLOAN_CONS1 primary key (loanid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

create index CASHLOAN_IDX1 on CASHLOAN(memberid) tablespace PSTTDA01;

--*********************************************
--drop table GOODS_SOLD_HDR cascade constraints;
create table GOODS_SOLD_HDR
(invid         number(10)  not null,
 invdt         date,
 billingdt     date,
 invamt        number(9,2),
 remarks       varchar2(200),
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint GOODS_SOLD_HDR_CONS1 primary key (invid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table GOODS_SOLD_DTL cascade constraints;
create table GOODS_SOLD_DTL
(invdtlid      number(10)  not null,
 invid         number(10)  not null,
 ismember      varchar2(1) default 'Y',                             *** new ***
 memberid      number(10),
 amount        number(9,2),
 balance       number(9,2),
 description   varchar2(100),
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint GOODS_SOLD_DTL_CONS1 primary key (invdtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table BILL_HDR cascade constraints;
create table BILL_HDR
(billid        number(10)  not null,
 compid        number(10)  not null,
 billdt        date,
 startdt       date,
 enddt         date,
 billamt       number(9,2),
 processdt     date,
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint BILL_HDR_CONS1 primary key (billid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table BILL_DTL cascade constraints;
create table BILL_DTL
(billdtlid        number(10)  not null,
 billid           number(10)  not null,
 ismember         varchar2(1) default 'Y',
 memberid         number(10),
 membername       varchar2(60),                  *** new ***
 contribution     number(9,2),
 cash_loanid      number(10),
 cash_amount      number(9,2),
 reg_loanid       number(10),
 reg_amount       number(9,2),
 educ_loanid      number(10),
 educ_amount      number(9,2),
 calamity_loanid  number(10),
 calamity_amount  number(9,2),
 emer_loanid      number(10),
 emer_amount      number(9,2),
 goods_amount     number(9,2),
 mem_total        number(9,2),
 insby            varchar2(15),
 insdt            date,
 updby            varchar2(15),
 upddt            date,
 constraint BILL_DTL_CONS1 primary key (billdtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table OR_HDR cascade constraints;
create table OR_HDR
(orid          number(10)  not null,
 compid        number(10)   not null,
 orno          varchar2(10),
 ordt          date,
 ordetail      varchar2(100),
 oramt         number(9,2),
 billid        number(10),
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint OR_HDR_CONS1 primary key (orid)
            using index tablespace PSTTDA01)

--*********************************************
--drop table OR_DTL cascade constraints;
create table OR_DTL
(ordtlid          number(10)  not null,
 orid             number(10)  not null,
 memberid         number(10)  not null,
 contibution      number(9,2),
 cash_loanid      number(10),
 cash_amount      number(9,2),
 reg_loanid       number(10),
 reg_amount       number(9,2),
 educ_loanid      number(10),
 educ_amount      number(9,2),
 calamity_loanid  number(10),
 calamity_amount  number(9,2),
 emer_loanid      number(10),
 emer_amount      number(9,2),
 goods_amount     number(9,2),
 memtotal         number(9,2),
 insby            varchar2(15),
 insdt            date,
 updby            varchar2(15),
 upddt            date,
 constraint OR_DTL_CONS1 primary key (ordtlid)
            using index tablespace PSTTDA01)

--*********************************************
--drop table SUBLEDGER_REG cascade constraints;
create table SUBLEDGER_REG
(ordtlid            number(10)  not null,
 loanid             number(10)  not null,
 loandtlid          number(10)  not null,
 mon_premium_prev   number(9,2),
 mon_interest_prev  number(9,2),
 mon_penalty_prev   number(9,2),
 premium_pay        number(9,2),
 interest_pay       number(9,2),
 penalty_pay        number(9,2),
 insby              varchar2(15),
 insdt              date,
 updby              varchar2(15),
 upddt              date,
 constraint SUBLEDGER_REG_CONS1 primary key (ordtlid,loanid,loandtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table SUBLEDGER_EDUC cascade constraints;
create table SUBLEDGER_EDUC
(ordtlid            number(10)  not null,
 loanid             number(10)  not null,
 loandtlid          number(10)  not null,
 mon_premium_prev   number(9,2),
 mon_interest_prev  number(9,2),
 mon_penalty_prev   number(9,2),
 premium_pay        number(9,2),
 interest_pay       number(9,2),
 penalty_pay        number(9,2),
 insby              varchar2(15),
 insdt              date,
 updby              varchar2(15),
 upddt              date,
 constraint SUBLEDGER_EDUC_CONS1 primary key (ordtlid,loanid,loandtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table SUBLEDGER_CAL cascade constraints;
create table SUBLEDGER_CAL
(ordtlid            number(10)  not null,
 loanid             number(10)  not null,
 loandtlid          number(10)  not null,
 mon_premium_prev   number(9,2),
 mon_interest_prev  number(9,2),
 mon_penalty_prev   number(9,2),
 premium_pay        number(9,2),
 interest_pay       number(9,2),
 penalty_pay        number(9,2),
 insby              varchar2(15),
 insdt              date,
 updby              varchar2(15),
 upddt              date,
 constraint SUBLEDGER_CAL_CONS1 primary key (ordtlid,loanid,loandtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table SUBLEDGER_EMER cascade constraints;
create table SUBLEDGER_EMER
(ordtlid            number(10)  not null,
 loanid             number(10)  not null,
 loandtlid          number(10)  not null,
 mon_premium_prev   number(9,2),
 mon_interest_prev  number(9,2),
 mon_penalty_prev   number(9,2),
 premium_pay        number(9,2),
 interest_pay       number(9,2),
 penalty_pay        number(9,2),
 insby              varchar2(15),
 insdt              date,
 updby              varchar2(15),
 upddt              date,
 constraint SUBLEDGER_EMER_CONS1 primary key (ordtlid,loanid,loandtlid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;

--*********************************************
--drop table PURCHASE_HDR cascade constraints;
create table PURCHASE_HDR
(purchid       number(10)  not null,
 suppid        number(10)  not null,
 pono          varchar2(10),
 purchdt       date,
 description   varchar2(100),
 purchamt      number(9,2),
 paymdetail    varchar2(100),
 paymdt        date,
 paymamt       number(9,2),
 balance       number(9,2),
 insby         varchar2(15),
 insdt         date,
 updby         varchar2(15),
 upddt         date,
 constraint PURCHASE_HDR_CONS1 primary key (purchid)
            using index tablespace PSTTDA01)
tablespace PSTTDA01;



--drop sequence COMPANY_SEQ;
create sequence COMPANY_SEQ start with 1 
increment by 1;

--drop sequence MEMBER_SEQ;
create sequence MEMBER_SEQ start with 1 
increment by 1;

--drop sequence SUPPLIER_SEQ;
create sequence SUPPLIER_SEQ start with 1 
increment by 1;




--*********************************************
--drop table IMS_GTEMP_SL;
create global temporary table IMS_GTEMP_SL
(tranid       number(7),
 trandt       date,
 insdt        date,
 sorter       varchar2(2),
 refno        varchar2(30),
 clinid       varchar2(7),
 insrid       varchar2(7),
 invno        varchar2(20),
 inslnname    varchar2(50),
 clinname     varchar2(80),
 insrname     varchar2(80),
 policyno     varchar2(50),
 endtno       varchar2(30),
 remarks      varchar2(100),
 dbamt        number(14,2),
 dbnoeff      varchar2(1) default 'N',
 cramt        number(14,2),
 crnoeff      varchar2(1) default 'N',
 runbal       number(14,2)
) on commit preserve rows;

