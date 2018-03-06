insert into  Team (`name`, `city`, `mascot`, `home_uniform`, `visit_uniform`) values('Team 6', 'Chicago', 'Cow', 'Red', 'Blue');
insert into  Team (`name`, `city`, `mascot`, `home_uniform`, `visit_uniform`) values('Team 7', 'Fairfield', 'Fish', 'Green', 'White');
insert into  Stadium (`name`, `city`, `state`) values('Chicago Football Stadium', 'Chicago', 'IL');
insert into  Stadium (`name`, `city`, `state`) values('Iowa Course Field', 'Fairfield', 'IA');

insert into match(`match_type`, `date`, `home_score`, `start_time`, `visitor_score`, `home_team_id`, `stadium_id`, `visitor_team_id`, `home_points`, `visitor_points`) values('TM', PARSEDATETIME('18 Feb 2018','dd MMM yyyy','en'), 2, PARSEDATETIME('16:30', 'HH:MM'), 3, 1, 1, 2, 2, 3); 
insert into match(`match_type`, `date`, `home_score`, `start_time`, `visitor_score`, `home_team_id`, `stadium_id`, `visitor_team_id`, `home_points`, `visitor_points`) values('TM', PARSEDATETIME('10 Feb 2018','dd MMM yyyy','en'), 2, PARSEDATETIME('07:30', 'HH:MM'), 3, 1, 1, 2, 2, 3); 