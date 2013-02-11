insert into speaker (id, activity,   compan,                      description, fullname,             jugmember, memberfct, photourl, url, email, personalurl)
             values (1, 'Architect', 'Agilent Technologies, Inc', 'Zozo',      'Olivier NOUGUIER',   true,      'OOO',     'photo',       'url', 'olivier.nouguier@gmail.com', 'http://www.orcades.net');

insert into event (id, capacity, "date",     description, location, map,   open, registrationurl, report, title  )
           values (1, 80,        '20130322', 'An event',  'Isim',   'Map', true, 'r url',         'report', 'the title' );
             
insert into talk (id, orderinevent, teaser,       datetime,            title,       event_id, speaker_id)
           values (1,  1,            'My teaser', '20121230 12:12:12', 'Ze talk',  1,        1);  
insert into talk (id, orderinevent, teaser,       datetime,            title,       event_id, speaker_id)
           values (2,  1,            'My teaser 2', '20121230 12:12:12', 'Ze talk 2',  1,        1);  
