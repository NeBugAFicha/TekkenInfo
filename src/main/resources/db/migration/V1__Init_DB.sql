create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );
create table guide (
    id bigint not null,
    body varchar(255) not null,
    title varchar(50) not null,
    author_id bigint not null,
    primary key (id)
) engine=InnoDB;
create table usr (
    id bigint not null,
    password varchar(255) not null,
    role varchar(255) not null,
    status varchar(255) not null,
    username varchar(30) not null,
    primary key (id)
) engine=InnoDB;
alter table guide
    add constraint guide_usr_fk foreign key (author_id) references usr (id);
create table characters
(
    name          varchar(255) not null,
    style         varchar(255) not null,
    tier          varchar(255) not null,
    image         varchar(255) not null,
    charMakerName varchar(255) not null
);