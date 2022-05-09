drop table if exists personaje_movie;


create table personaje_movie
(
    personaje_id bigint not null,
    movie_id   bigint not null,
    primary key (personaje_id, movie_id),
    key movie_id (movie_id),
    constraint fk_personaje_movie_1
        foreign key (personaje_id) references personaje (personaje_id),
    constraint fk_personaje_movie_2
        foreign key (movie_id) references movie (movie_id)
) engine = InnoDB;
