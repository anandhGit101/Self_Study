INSERT INTO `justbooktickets`.`cinemas`(`cinemas_id`,`cinemas_location`, `cinemas_address`, `cinemas_name`)
VALUES (1, 'Chennai', 'The Grand Square Mall, Velachery', 'PVR Cinemas - Grand Mall'),
(2, 'Chennai', 'Ampa Skywalk, Royapettah', 'PVR Cinemas - SkyWalk'), 
(3, 'Chennai', 'Grand Galada, Pallavaram', 'PVR Cinemas - Grand Galada'),
(4, 'Chennai', 'VR Mall, Metro Zone, Anna Nagar', 'PVR Cinemas - Chennai Metro Zone'),
(5, 'Chennai', 'Play House Heritage Rsl Ecr, Chennai', 'PVR Cinemas - Play House Heritage'),
(6, 'Chennai', 'SKLS GALAXY MALL, Red Hills', 'PVR Cinemas - SKLS Galaxy Mall'),
(7, 'Chennai', 'Pheonix Market City, Velachery','Jazz Cinemas Luxe');

INSERT INTO `justbooktickets`.`cinemas`(`cinemas_location`, `cinemas_address`, `cinemas_name`)
VALUES ('Bengaluru', 'Meenakshi Mall, Bannerghatta Road', 'Cinepolis - Royal'),
('Bengaluru', 'Grand Sigma Mall, Vasanth Nagar', 'Cinepolis Fun Cinemas'),
('Bengaluru', 'Gopalan Arcade Mall, Rajarajeswari Nagar', 'Gopalan Cinemas'),
('Bengaluru', 'Central Mall, JP Nagar', 'INOX'),
('Bengaluru', 'Marathahalli', 'Innovative Multiplex'),
('Bengaluru', 'Tavarekere', 'Balaji Theatre'),
('Bengaluru', 'Banashankari 2nd Stage', 'Kamakya Theatre');

INSERT INTO `justbooktickets`.`cinemas`(`cinemas_location`, `cinemas_address`, `cinemas_name`)
VALUES ('Hyderabad', 'Trinity Mall, Kukatpally', 'Cinepolis - Manjeera'),
('Hyderabad', 'Mantra Mall, Attapur', 'Cinepolis'),
('Hyderabad', 'Maheshwari Parmeshwari Mall, Kachiguda', 'INOX'),
('Hyderabad', 'Dilsukh Nagar', 'Miraj Cinemas'),
('Hyderabad', 'Next Galleria Mall, Panjagutta', 'PVR Cinemas');

INSERT INTO `justbooktickets`.`cinemas`(`cinemas_location`, `cinemas_address`, `cinemas_name`)
VALUES ('New Delhi', 'East of Kailash', 'M Cinemas'),
('New Delhi', 'Unity One Mall Rohini', 'Cinepolis'),
('New Delhi', 'TDI Mall, Moti Nagar', 'Fun Cinemas'),
('New Delhi', 'Vasant Kunj', 'PVR Promenade'),
('New Delhi', 'Jangpura Extension', 'INOX Eros One');

INSERT INTO `justbooktickets`.`cinemas`(`cinemas_location`, `cinemas_address`, `cinemas_name`)
VALUES ('Mumbai', 'Mumbai Central', 'Maratha Mandir'),
('Mumbai', 'Le Reve-Globus Mall, Bandra West', 'SPI Cinemas'),
('Mumbai', 'Santacruz', 'Gold Cinema'),
('Mumbai', 'R Odeon Mall, Ghatkopar', 'Cinemax'),
('Mumbai', 'Anand Cinema, Thane', 'Fun Cinemas'),
('Mumbai', 'Metro Mall Junction, Kalyan(E)', 'INOX');


INSERT INTO `justbooktickets`.`movie`(`movie_cast`, `movie_genre`, `movie_language`, `movie_release_date`, `movie_synopsis`, `movie_title`)
VALUES ('Ajithkumar, Nayanthara, Namitha, Prabhu Sivaji Ganesan, Rahman', 'Action-Thriller', 'Tamil', (DATE'2007-12-14'), 'Billa is a 2007 Indian Tamil-language action thriller film directed by Vishnuvardhan. It is a reboot to 1980 Tamil film Billa. The underworld don Billa (Ajith Kumar), featured on Interpols criminal list, is hiding and operating out of Malaysia. DSP Jayaprakash (Prabhu) has spent the last few years looking for Billa, leaving behind a life in India. During a chase with the police, Billa is severely wounded after an accident and dies in front of the DSP. The DSP then secretly holds a burial of Billa.The DSP trains Velu and sends him back to Billas gang as a person who has lost his memory. Slowly Velu starts to learn about Billas gang, even speaks with Jagdish, Billa boss, on the phone.','Billa'),
('Rana Daggubati, Ajithkumar, Nayanthara, Arya, Tapsee, Atul Kulkarni', 'Action-Thriller', 'Tamil', (DATE'2013-10-13'), 'Arrambam is a 2013 Indian Tamil-language action thriller film directed by Vishnuvardhan.The film starts with Ashok Kumar aka AK (Ajith Kumar) setting bombs in three places and informing the police about them. Before the police arrive, the bombs are set off. AK, along with his lady love Maya (Nayanthara), threatens her old college mate hacker Arjun (Arya), who is forced to hack one system after another. AK then threatens Sriram Raghavan (Aadukalam Naren) by trying to kill his child so that he can extract the truth about black money. AK then murders Sriram. Arjun, who is irritated by the things happening around him, complains about AKs misdeeds to Inspector Prakash (Kishore). When AK gets closer to achieving his goals, Arjun gives him away to the police, and both are arrested. Arjun is later released after his true identity is revealed.','Arrambam'),
('Yash, Srinidhi Shetty, Anant Nag, Vasishta N. Simha', 'Period Action', 'Kannada', (DATE'2018-12-21'), 'K.G.F: Chapter 1 is a 2018 Indian Kannada-language period action film written and directed by Prashanth Neel, and produced by Vijay Kiragandur under the banner Hombale films. It is the first instalment in the two-part series, followed by K.G.F: Chapter 2. Journalist Anand Ingalagis book El-Dorado, that detailed events at the Kolar Gold Fields between 1951 and present day, has been banned by the Government of India and all published copies burnt. However, a television news channel procures a copy and interviews him circling the events.','K.G.F: Chapter 1'),
('Darsheel Safary, Aamir Khan, Tisca Chopra, Vipin Sharma, Sachet Engineer, Tanay Chheda', 'Drama', 'Hindi', (DATE'2018-12-21'), 'Taare Zameen Par (titled Like Stars on Earth internationally) is a 2007 Indian drama film produced and directed by Aamir Khan. The film explores the life and imagination of Ishaan, an 8-year-old dyslexic child. Although he excels in art, his poor academic performance leads his parents to send him to a boarding school. Ishaans new art teacher suspects that he is dyslexic and helps him to overcome his disability. ','Taare Zameen Par');

INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('1', '2019-09-09', '11:15', '1', '1');
INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('2', '2019-09-09', '13:45', '5', '1');
INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('3', '2019-09-09', '19:05', '10', '1');
INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('4', '2019-09-09', '12:00', '15', '1');
INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('5', '2019-09-09', '11:15', '3', '3');
INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('6', '2019-09-09', '15:15', '5', '3');
INSERT INTO `justbooktickets`.`movie_schedule` (`movie_schedule_id`, `show_date`, `show_timings`, `cinemas_id`, `movie_id`) VALUES ('7', '2019-09-09', '16:30', '15', '3');