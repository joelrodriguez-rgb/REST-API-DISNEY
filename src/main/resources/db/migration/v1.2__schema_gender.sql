drop table if exists gender;

create table gender(
    gender_id bigint  not null auto_increment,
    gender_name varchar(255) not null,
    img_gender varchar(255) null,
    primary key (gender_id)
) engine = InnoDB;