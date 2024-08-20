create sequence vehicle_seq increment by 5 start 1;
create sequence vehicle_calc_column_seq increment by 5 start 1;

create table vehicle (
  id bigint not null,
  updated_at timestamptz not null,
  reg_num varchar(10) not null,
  constraint vehicle_pkey primary key (id)
);

create table vehicle_calc_column (
  id bigint not null,
  vehicle_id bigint not null,
  title varchar(64) not null,
  constraint vehicle_calc_column_pkey primary key (id),
  constraint vehicle_calc_column_fk_vehicle foreign key (vehicle_id) references vehicle(id) on delete cascade on update cascade
);
