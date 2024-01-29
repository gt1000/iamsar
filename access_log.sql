drop table if exists access_log cascade;
drop table if exists access_log_2022 cascade;
drop table if exists access_log_2023 cascade;
drop table if exists access_log_2024 cascade;
commit;


-- 관리자 페이지 사용 이력
create table access_log(
	access_log_id				bigserial,
	user_name					varchar(64),
	year						char(4)				default to_char(now(), 'YYYY'),
	month						varchar(2)			default to_char(now(), 'MM'),
	day							varchar(2)			default to_char(now(), 'DD'),
	year_week					varchar(2)			default to_char(now(), 'WW'),
	week						varchar(2)			default to_char(now(), 'W'),
	hour						varchar(2)			default to_char(now(), 'HH24'),
	minute						varchar(2)			default to_char(now(), 'MI'),
	created_date				timestamp with time zone			default now()
) partition by range(created_date);

comment on table access_log is '관리자 페이지 사용 이력';
comment on column access_log.access_log_id is '고유번호';
comment on column access_log.user_name is '사용자 이름';
comment on column access_log.year is '년';
comment on column access_log.month is '월';
comment on column access_log.day is '일';
comment on column access_log.year_week is '일년중 몇주';
comment on column access_log.week is '이번달 몇주';
comment on column access_log.hour is '시간';
comment on column access_log.minute is '분';
comment on column access_log.created_date is '등록일';


create table access_log_2022 partition of access_log for values from ('2022-01-01') to ('2023-01-01');
create table access_log_2023 partition of access_log for values from ('2023-01-01') to ('2024-01-01');
create table access_log_2024 partition of access_log for values from ('2024-01-01') to ('2025-01-01');


-- index 의 경우 부모 테이블에만 만들면 됨
create index access_log_idx on access_log (created_date);
commit;

-- constraint 는 부모 테이블에 생성 되지 않고 자식 테이블에 만들어야 함. index 를 잘 타는지는 테스트 필요.
alter table only access_log_2022 add constraint access_log_2022_pk primary key (access_log_id);
alter table only access_log_2023 add constraint access_log_2023_pk primary key (access_log_id);
alter table only access_log_2024 add constraint access_log_2024_pk primary key (access_log_id);
commit;