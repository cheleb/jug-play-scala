# Play Jug Schema

# --- !Ups

    create table Event (
        id bigserial,
        capacity integer not null,
        date timestamp,
        description text,
        location text,
        map text,
        open boolean not null,
        registrationURL text,
        report text,
        title text,
        primary key (id)
    );

    create table Participation (
        id bigserial,
        code text,
        status integer,
        event_id bigint,
        user_id bigint,
        primary key (id)
    );

    create table Speaker (
        id bigserial,
        activity text,
        compan text,
        description text,
        fullName text,
        jugmember boolean,
        memberFct text,
        photoUrl text,
        url text,
        primary key (id)
    );

    create table Talk (
        id bigserial,
        orderInEvent integer not null,
        tags text,
        teaser varchar(254),
        datetime text,
        title text,
        event_id bigint,
        speaker_id bigint,
        primary key (id)
    );

    create table "User" (
        id bigserial,
        email text,
        primary key (id)
    );

    alter table Participation
        add constraint FKE5A0BD21BAFEA2D6
        foreign key (event_id)
        references Event (id);

    alter table Participation
        add constraint FKE5A0BD2147140EFE
        foreign key (user_id)
        references "User" (id);

    alter table Talk
        add constraint FK27A8CCBAFEA2D6
        foreign key (event_id)
        references Event (id);

    alter table Talk
        add constraint FK27A8CCF3EB05B6
        foreign key (speaker_id)
        references Speaker (id);


# --- !Downs

    alter table Participation
        drop
        foreign key FKE5A0BD21BAFEA2D6;

    alter table Participation
        drop
        foreign key FKE5A0BD2147140EFE;

    alter table Talk
        drop
        foreign key FK27A8CCBAFEA2D6;

    alter table Talk
        drop
        foreign key FK27A8CCF3EB05B6;

    drop table if exists Event;

    drop table if exists Participation;

    drop table if exists Speaker;

    drop table if exists Talk;

    drop table if exists "User";

