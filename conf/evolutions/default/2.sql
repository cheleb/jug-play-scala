# Play Jug 0.0.2 Schema

# --- !Ups

    alter table Event add partner_id bigint;

    create table EventPartner (
        id serial,
        description text,
        logoURL text,
        name text,
        url text,
        primary key (id)
    );

    create table News (
        id serial,
        comments bit not null,
        content text,
        date timestamp,
        title text,
        primary key (id)
    );

    alter table Talk drop column tags;

    alter table Speaker add email text;

    alter table Speaker add personalUrl text;

    create table Tag (
        id serial,
        name text,
        primary key (id)
    );

    create table Talk_Tag (
        Talk_id bigint not null,
        tags_id bigint not null,
        primary key (Talk_id, tags_id)
    );

    create table YearPartner (
        id serial,
        description text,
        logoURL text,
        name text,
        startDate timestamp,
        stopDate timestamp,
        url text,
        primary key (id)
    );
    
    create table Poll (
        id serial,
        question text not null,
        expiryDate timestamp,
        visible boolean,
        primary key (id)
    );

    create table Answer (
        id serial,
        answer text not null,
        votes bigint,
        poll_id bigint not null,
        primary key (id)
    );

    alter table Event
        add constraint FK403827AA33E91E4
        foreign key (partner_id)
        references EventPartner (id);

    alter table Talk_Tag
        add constraint FKDF7AD987222C70F7
        foreign key (tags_id)
        references Tag (id);

    alter table Talk_Tag
        add constraint FKDF7AD987F3D5575E
        foreign key (Talk_id)
        references Talk (id);

    alter table Answer
        add constraint Poll_Answer
        foreign key (poll_id)
        references Poll (id);

# --- !Downs

    alter table Event
        drop
        foreign key FK403827AA33E91E4;

    alter table Talk_Tag
        drop
        foreign key FKDF7AD987222C70F7;

    alter table Talk_Tag
        drop
        foreign key FKDF7AD987F3D5575E;

    alter table Answer
        drop
        foreign key Poll_Answer;

    alter table Event drop column partner_id;

    alter table Speaker drop email;

    alter table Speaker drop personalUrl;

    alter table Talk add tags text;

    drop table if exists EventPartner;

    drop table if exists News;

    drop table if exists Tag;

    drop table if exists Talk_Tag;

    drop table if exists YearPartner;

    drop table if exists Poll;

    drop table if exists Answer;
