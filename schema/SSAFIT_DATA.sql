USE SSAFIT;

-- 1. 운동 목표 유형 데이터 삽입
INSERT INTO goal_type (title, description) VALUES 
('체중 감량', '체지방 감소를 최우선 목표로 합니다.'),
('근력 강화', '근육량의 증가와 근력 향상을 최우선 목표로 합니다.'),
('체력 향상', '전체적인 체력 향상을 최우선 목표로 합니다.'),
('유연성 향상', '신체의 유연성 향상을 최우선 목표로 합니다.'),
('심폐 지구력 향상', '심폐지구력 향상을 최우선 목표로 합니다.');


-- 2. 운동 도구/장비 데이터 삽입
INSERT INTO workout_tool (tool_name) VALUES 
('덤벨'),
('요가매트'),
('저항 밴드'),
('케틀벨');

-- 3. 영상 태그 데이터 삽입
INSERT INTO video_tag (tag) VALUES 
('상체'),
('하체'),
('코어'),
('전신'),
('등'),
('이두/삼두'),
('가슴'),
('스트레칭'),
('유산소');

-- 4. 게시글 카테고리 데이터 삽입
INSERT INTO post_category (category) VALUES 
('운동 인증'),
('질문'),
('자유');



-- 5. user 테이블
INSERT INTO user (id, login_id, email, pwd, role, profile_img_url, first_name, last_name, nickname, age, birthdate, gender, reg_date, height, weight, goal_type, streak_days, max_streak_days, total_workout_days, total_workout_minutes, total_calories, level, exp, coin, group_id, marketing_agree, active, update_date) VALUES
(1, 'admin', 'admin@example.com', 'admin', 'ADMIN', 'https://cdn.example.com/profiles/U0001.png', 'admin', 'admin', 'admin', 0, '1995-01-03', 'F', '2025-02-23 07:00:00', 0.0, 0.0, 1, 0, 0, 0, 0, 0, 0, 0, 0, NULL, 0, 1, '2025-03-09 07:00:00'),
(2, 'youngho.han2', 'youngho.han2@example.com', '$2a$10$mockhashedpasswordvalue02', 'USER', 'https://cdn.example.com/profiles/U0002.png', 'Youngho', 'Han', 'fitfox2', 32, '1993-07-31', 'M', '2025-06-24 18:00:00', 155.4, 88.1, 3, 5, 53, 52, 1040, 17316, 12, 41664, 17335, NULL, 1, 1, '2025-07-28 18:00:00'),
(3, 'seungmin.lee3', 'seungmin.lee3@example.com', '$2a$10$mockhashedpasswordvalue03', 'USER', 'https://cdn.example.com/profiles/U0003.png', 'Seungmin', 'Lee', 'ironheart3', 29, '1996-07-29', 'F', '2025-07-06 04:00:00', 156.7, 48.1, 2, 5, 34, 148, 2960, 50912, 9, 25713, 41660, NULL, 1, 1, '2025-07-28 04:00:00'),
(4, 'jimin.choi4', 'jimin.choi4@example.com', '$2a$10$mockhashedpasswordvalue04', 'USER', 'https://cdn.example.com/profiles/U0004.png', 'Jimin', 'Choi', 'gainseeker4', 21, '2004-03-29', 'F', '2025-10-02 09:00:00', 158.6, 65.8, 3, 44, 79, 327, 6540, 163500, 11, 48972, 3665, NULL, 1, 1, '2025-10-27 09:00:00'),
(5, 'hyunseo.jung5', 'hyunseo.jung5@example.com', '$2a$10$mockhashedpasswordvalue05', 'USER', 'https://cdn.example.com/profiles/U0005.png', 'Hyunseo', 'Jung', 'gainseeker5', 28, '1997-01-21', 'F', '2025-09-13 22:00:00', 181.0, 73.9, 2, 8, 39, 135, 5400, 58995, 18, 37368, 48956, NULL, 0, 1, '2025-10-06 22:00:00'),
(6, 'seojin.choi6', 'seojin.choi6@example.com', '$2a$10$mockhashedpasswordvalue06', 'USER', 'https://cdn.example.com/profiles/U0006.png', 'Seojin', 'Choi', 'stretchy6', 33, '1992-01-15', 'M', '2025-02-26 14:00:00', 172.0, 80.6, 4, 4, 53, 305, 9150, 138775, 15, 47505, 16476, NULL, 1, 1, '2025-04-10 14:00:00'),
(7, 'jimin.shin7', 'jimin.shin7@example.com', '$2a$10$mockhashedpasswordvalue07', 'USER', 'https://cdn.example.com/profiles/U0007.png', 'Jimin', 'Shin', 'runbunny7', 29, '1996-08-01', 'M', '2025-03-23 00:00:00', 150.1, 77.5, 3, 48, 59, 256, 10240, 52224, 10, 44470, 41874, NULL, 1, 1, '2025-04-01 00:00:00'),
(8, 'yuna.kang8', 'yuna.kang8@example.com', '$2a$10$mockhashedpasswordvalue08', 'USER', 'https://cdn.example.com/profiles/U0008.png', 'Yuna', 'Kang', 'ironheart8', 35, '1990-01-05', 'F', '2025-06-16 01:00:00', 150.7, 86.8, 5, 3, 33, 122, 4880, 23180, 3, 11991, 31849, NULL, 1, 1, '2025-06-24 01:00:00'),
(9, 'jaehee.han9', 'jaehee.han9@example.com', '$2a$10$mockhashedpasswordvalue09', 'USER', 'https://cdn.example.com/profiles/U0009.png', 'Jaehee', 'Han', 'corequeen9', 24, '2001-11-01', 'F', '2025-08-05 16:00:00', 182.5, 79.0, 2, 19, 70, 365, 14600, 175930, 12, 33528, 33919, NULL, 1, 1, '2025-08-19 16:00:00'),
(10, 'taemin.lee10', 'taemin.lee10@example.com', '$2a$10$mockhashedpasswordvalue10', 'USER', 'https://cdn.example.com/profiles/U0010.png', 'Taemin', 'Lee', 'gainseeker10', 23, '2002-06-04', 'F', '2025-10-29 17:00:00', 150.3, 76.9, 1, 4, 8, 117, 3510, 21762, 17, 33558, 18250, NULL, 0, 1, '2025-12-02 17:00:00'),
(11, 'youngho.park11', 'youngho.park11@example.com', '$2a$10$mockhashedpasswordvalue11', 'USER', 'https://cdn.example.com/profiles/U0011.png', 'Youngho', 'Park', 'stretchy11', 30, '1995-06-14', 'M', '2025-07-28 16:00:00', 153.3, 74.7, 3, 26, 85, 216, 8640, 38232, 4, 4992, 26386, NULL, 0, 1, '2025-08-12 16:00:00'),
(12, 'hana.choi12', 'hana.choi12@example.com', '$2a$10$mockhashedpasswordvalue12', 'USER', 'https://cdn.example.com/profiles/U0012.png', 'Hana', 'Choi', 'stretchy12', 32, '1993-02-22', 'M', '2025-04-04 18:00:00', 166.2, 84.4, 5, 51, 86, 226, 4520, 39550, 18, 79632, 967, NULL, 1, 1, '2025-04-30 18:00:00'),
(13, 'hojun.han13', 'hojun.han13@example.com', '$2a$10$mockhashedpasswordvalue13', 'USER', 'https://cdn.example.com/profiles/U0013.png', 'Hojun', 'Han', 'cardioking13', 34, '1991-04-26', 'F', '2025-07-14 10:00:00', 184.5, 56.9, 4, 27, 89, 146, 2920, 36062, 10, 18910, 3832, NULL, 1, 1, '2025-08-30 10:00:00'),
(14, 'jisoo.kang14', 'jisoo.kang14@example.com', '$2a$10$mockhashedpasswordvalue14', 'USER', 'https://cdn.example.com/profiles/U0014.png', 'Jisoo', 'Kang', 'gainseeker14', 25, '2000-09-10', 'F', '2025-09-29 15:00:00', 152.0, 67.9, 2, 4, 90, 35, 700, 12460, 4, 19424, 37334, NULL, 1, 1, '2025-11-07 15:00:00'),
(15, 'dongho.lee15', 'dongho.lee15@example.com', '$2a$10$mockhashedpasswordvalue15', 'USER', 'https://cdn.example.com/profiles/U0015.png', 'Dongho', 'Lee', 'gainseeker15', 24, '2001-09-22', 'U', '2025-05-14 16:00:00', 173.4, 59.1, 3, 8, 90, 202, 6060, 77568, 11, 52855, 49274, NULL, 1, 1, '2025-06-22 16:00:00'),
(16, 'jiwon.hwang16', 'jiwon.hwang16@example.com', '$2a$10$mockhashedpasswordvalue16', 'USER', 'https://cdn.example.com/profiles/U0016.png', 'Jiwon', 'Hwang', 'ironheart16', 31, '1994-10-13', 'F', '2025-05-16 14:00:00', 182.7, 84.6, 2, 18, 38, 189, 5670, 80892, 10, 35050, 42858, NULL, 1, 1, '2025-07-14 14:00:00'),
(17, 'seojin.lee17', 'seojin.lee17@example.com', '$2a$10$mockhashedpasswordvalue17', 'USER', 'https://cdn.example.com/profiles/U0017.png', 'Seojin', 'Lee', 'runbunny17', 33, '1992-05-26', 'F', '2025-03-21 18:00:00', 159.9, 54.5, 3, 43, 83, 104, 3120, 42432, 16, 32448, 3329, NULL, 1, 1, '2025-05-13 18:00:00'),
(18, 'jisoo.jung18', 'jisoo.jung18@example.com', '$2a$10$mockhashedpasswordvalue18', 'USER', 'https://cdn.example.com/profiles/U0018.png', 'Jisoo', 'Jung', 'legday18', 33, '1992-12-07', 'U', '2025-03-25 09:00:00', 165.5, 76.8, 1, 4, 23, 57, 2280, 9576, 12, 40620, 36210, NULL, 0, 1, '2025-03-27 09:00:00'),
(19, 'jungkook.jung19', 'jungkook.jung19@example.com', '$2a$10$mockhashedpasswordvalue19', 'USER', 'https://cdn.example.com/profiles/U0019.png', 'Jungkook', 'Jung', 'legday19', 31, '1994-09-17', 'F', '2025-02-22 21:00:00', 177.3, 84.8, 5, 47, 56, 317, 6340, 73861, 6, 27660, 27020, NULL, 1, 1, '2025-04-13 21:00:00'),
(20, 'eunseo.yoon20', 'eunseo.yoon20@example.com', '$2a$10$mockhashedpasswordvalue20', 'USER', 'https://cdn.example.com/profiles/U0020.png', 'Eunseo', 'Yoon', 'homebeast20', 20, '2005-09-23', 'M', '2025-07-15 11:00:00', 180.1, 55.0, 4, 19, 48, 179, 3580, 28998, 7, 18424, 21512, NULL, 1, 1, '2025-08-06 11:00:00'),
(21, 'seungmin.shin21', 'seungmin.shin21@example.com', '$2a$10$mockhashedpasswordvalue21', 'USER', 'https://cdn.example.com/profiles/U0021.png', 'Seungmin', 'Shin', 'legday21', 35, '1990-08-15', 'M', '2025-05-14 15:00:00', 170.3, 89.3, 1, 38, 65, 55, 1650, 17050, 14, 48762, 33516, NULL, 0, 1, '2025-05-30 15:00:00'),
(22, 'dongho.kim22', 'dongho.kim22@example.com', '$2a$10$mockhashedpasswordvalue22', 'USER', 'https://cdn.example.com/profiles/U0022.png', 'Dongho', 'Kim', 'ironheart22', 23, '2002-01-28', 'F', '2025-07-06 23:00:00', 152.4, 74.9, 3, 20, 35, 319, 12760, 96657, 17, 38522, 43705, NULL, 0, 1, '2025-08-19 23:00:00'),
(23, 'jaehee.jung23', 'jaehee.jung23@example.com', '$2a$10$mockhashedpasswordvalue23', 'USER', 'https://cdn.example.com/profiles/U0023.png', 'Jaehee', 'Jung', 'yogacat23', 26, '1999-06-07', 'M', '2025-04-01 05:00:00', 169.9, 63.3, 1, 18, 44, 155, 4650, 69130, 20, 73620, 21118, NULL, 0, 1, '2025-05-14 05:00:00'),
(24, 'gyuri.choi24', 'gyuri.choi24@example.com', '$2a$10$mockhashedpasswordvalue24', 'USER', 'https://cdn.example.com/profiles/U0024.png', 'Gyuri', 'Choi', 'homebeast24', 21, '2004-10-11', 'M', '2025-05-27 02:00:00', 173.2, 72.9, 1, 43, 62, 120, 2400, 30120, 5, 5500, 3028, NULL, 0, 1, '2025-06-25 02:00:00'),
(25, 'youngho.yoon25', 'youngho.yoon25@example.com', '$2a$10$mockhashedpasswordvalue25', 'USER', 'https://cdn.example.com/profiles/U0025.png', 'Youngho', 'Yoon', 'cardioking25', 24, '2001-02-01', 'M', '2025-05-05 14:00:00', 173.0, 45.2, 1, 14, 36, 217, 8680, 90055, 15, 18075, 36530, NULL, 1, 1, '2025-05-13 14:00:00'),
(26, 'gyuri.han26', 'gyuri.han26@example.com', '$2a$10$mockhashedpasswordvalue26', 'USER', 'https://cdn.example.com/profiles/U0026.png', 'Gyuri', 'Han', 'gainseeker26', 28, '1997-02-12', 'M', '2025-11-11 09:00:00', 181.2, 64.2, 4, 47, 77, 81, 2430, 22842, 8, 35520, 41789, NULL, 1, 1, '2025-12-21 09:00:00'),
(27, 'jimin.choi27', 'jimin.choi27@example.com', '$2a$10$mockhashedpasswordvalue27', 'USER', 'https://cdn.example.com/profiles/U0027.png', 'Jimin', 'Choi', 'runbunny27', 29, '1996-05-29', 'F', '2025-05-20 20:00:00', 161.2, 69.3, 2, 14, 63, 77, 3080, 17556, 7, 8841, 27188, NULL, 0, 1, '2025-06-15 20:00:00'),
(28, 'hana.kim28', 'hana.kim28@example.com', '$2a$10$mockhashedpasswordvalue28', 'USER', 'https://cdn.example.com/profiles/U0028.png', 'Hana', 'Kim', 'cardioking28', 22, '2003-02-06', 'M', '2025-10-22 22:00:00', 166.7, 87.4, 3, 54, 80, 199, 7960, 85371, 20, 93540, 14453, NULL, 1, 1, '2025-11-18 22:00:00'),
(29, 'minseo.han29', 'minseo.han29@example.com', '$2a$10$mockhashedpasswordvalue29', 'USER', 'https://cdn.example.com/profiles/U0029.png', 'Minseo', 'Han', 'legday29', 20, '2005-01-01', 'M', '2025-03-27 00:00:00', 182.2, 89.1, 5, 37, 73, 201, 8040, 32763, 3, 10896, 28089, NULL, 0, 1, '2025-03-30 00:00:00'),
(30, 'seungmin.jung30', 'seungmin.jung30@example.com', '$2a$10$mockhashedpasswordvalue30', 'USER', 'https://cdn.example.com/profiles/U0030.png', 'Seungmin', 'Jung', 'yogacat30', 25, '2000-03-13', 'U', '2025-06-22 22:00:00', 159.7, 87.8, 4, 53, 58, 129, 3870, 20511, 18, 21834, 22935, 1.0, 1, 1, '2025-08-10 22:00:00');


-- 6. workout_group 데이터 추가
INSERT INTO workout_group (id, group_name, master_id, is_deleted) VALUES
(1, '초보 환영', 1, FALSE),
(2, '같이 성장해요', 5, FALSE),
(3, '헬스장 지박령 모임', 10, FALSE),
(4, '전신 챌린지 그룹', 15, FALSE),
(5, '3대 500 이상만', 20, FALSE);


-- 5-2. 회원-그룹 외래키 추가
-- Group 1 (master = 1) → members: 1,2,3,4,6,7
UPDATE user SET group_id = 1 WHERE id IN (1, 2, 3, 4, 7);

-- Group 2 (master = 5) → members: 5,8,9,11,12,13
UPDATE user SET group_id = 2 WHERE id IN (5, 8, 9, 11, 13);

-- Group 3 (master = 10) → members: 10,14,16,17,18,19
UPDATE user SET group_id = 3 WHERE id IN (10, 14, 16, 19);

-- Group 4 (master = 15) → members: 15,21,22,23,24,25
UPDATE user SET group_id = 4 WHERE id IN (15, 21, 22, 24, 25);

-- Group 5 (master = 20) → members: 20,26,27,28,29,30
UPDATE user SET group_id = 5 WHERE id IN (20, 26, 27, 30);


-- 7. follow
INSERT INTO follow (follower_id, followee_id) VALUES
(2, 1),
(3, 1),
(4, 1),
(6, 3),
(7, 2),
(8, 5),
(9, 5),
(10, 3),
(12, 8),
(15, 10);


-- 8. post
INSERT INTO post (user_id, category, title, content, image_urls) VALUES
(2, 1, '오늘도 운동 완료!', '하루 30분 루틴 성공했습니다!', NULL),
(3, 1, '하체 불폭탄🔥', '하체 루틴 40분… 살려줘…', NULL),
(5, 2, '덤벨 무게 추천 부탁!', '여자 평균 덤벨 무게가 어떤가요?', NULL),
(7, 3, '오늘의 식단 공유', '운동보다 식단이 어려움…', NULL),
(8, 1, '상체 루틴 인증!', '팔이 후들거립니다', NULL),
(10, 3, '운동 메이트 구해요', '같이 홈트할 사람~~', NULL),
(12, 1, '데일리 스트레칭 완료', '매일 스트레칭 20분 챌린지 중.', NULL),
(14, 2, '근력운동 루틴 질문', '3분할 루틴 어떤가요?', NULL),
(17, 1, '점심 헬스장 털었습니다', '점심시간 러닝 3km!', NULL),
(20, 3, '홈트 장비 추천', '요가매트 브랜드 뭐 쓰세요?', NULL);


-- 9. comment
INSERT INTO comment (post_id, user_id, content) VALUES
(1, 3, '멋져요! 꾸준함 최고!'),
(1, 4, '대단합니다🔥'),
(2, 7, '저도 오늘 하체 했어요 ㅠㅠ'),
(2, 10, '진짜 힘들죠… 공감합니다'),
(3, 8, '2kg~4kg 추천해요!'),
(3, 12, '저는 3kg 씁니다!'),
(4, 2, '맛있어 보이네요!'),
(4, 5, '식단이 더 힘들죠 😂'),
(5, 6, '상체 루틴 추천해주세요!'),
(5, 7, '저도 인증합니다 👍'),
(6, 15, '같이 해요!'),
(6, 17, '지역이 어디세요?'),
(7, 3, '스트레칭 중요하죠!'),
(7, 8, '저도 매일 하려고요'),
(8, 10, '3분할 좋아요!'),
(8, 13, '루틴 공유해요!'),
(9, 12, '점심에 운동이라니 대단!'),
(9, 18, '저도 점심러닝 도전해볼게요'),
(10, 23, '저는 요가매트 A사 추천!'),
(10, 25, '저는 쿠션 두꺼운 걸 선호해요!');


-- 10. mission
INSERT INTO mission (mission_name, description) VALUES
('첫 운동 시작', '첫 운동을 완료하세요'),
('3일 연속 운동', '3일 동안 연속으로 운동하세요'),
('10일 연속 운동', '10일 동안 연속 운동 성공'),
('30분 운동 달성', '운동 30분 이상을 달성하세요'),
('칼로리 500 소모', '총 500 칼로리를 소모하세요');


-- 11. mission_record
INSERT INTO mission_record (mission_id, user_id) VALUES
(1, 2),
(1, 3),
(2, 2),
(2, 5),
(3, 7),
(4, 10),
(4, 12),
(5, 3),
(5, 8),
(5, 20);


-- 12. playlist
INSERT INTO playlist (publisher_id, title, description) VALUES
(2, '상체 강화 루틴', '상체 전용 홈트 플레이리스트'),
(5, '전신 챌린지 7일', '전신 루틴 모음'),
(10, '하체 불🔥루틴', '하체 집중 루틴 모음');


-- 13.video-tag-map
-- 비디오 1 (FULL_BODY)
INSERT INTO video_tag_map (video_id, tag_id) VALUES
(1, 4), -- 전신
(1, 9); -- 유산소

-- 비디오 2 (FULL_BODY)
INSERT INTO video_tag_map (video_id, tag_id) VALUES
(2, 4),
(2, 3);  -- 코어 포함

-- 비디오 3 (FULL_BODY)
INSERT INTO video_tag_map (video_id, tag_id) VALUES
(3, 4),
(3, 9);

-- 비디오 4 (MIXED)
INSERT INTO video_tag_map (video_id, tag_id) VALUES
(4, 4),
(4, 2);  -- 하체 포함

-- 비디오 5 (FULL_BODY)
INSERT INTO video_tag_map (video_id, tag_id) VALUES
(5, 4),
(5, 9);

-- 14. video-tool map
INSERT INTO video_tool_map (video_id, tool_id) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2);


-- 15. workout_record
INSERT INTO workout_record 
(id, user_id, video_id, start_time, end_time, user_satisfaction, personal_difficulty, user_memo, is_success)
VALUES
(1, 2, 1, '2025-02-01 10:00:00', '2025-02-01 10:20:00', 4, 3, '땀 많이 남', TRUE),
(2, 3, 1, '2025-02-02 09:00:00', '2025-02-02 09:20:00', 5, 2, '쉬웠음', TRUE),
(3, 5, 2, '2025-02-03 20:00:00', '2025-02-03 20:12:00', 3, 3, NULL, TRUE),
(4, 7, 3, '2025-02-04 21:00:00', '2025-02-04 21:20:00', 4, 4, '매웠다', TRUE),
(5, 8, 4, '2025-02-05 08:00:00', '2025-02-05 08:16:00', 2, 3, NULL, FALSE),
(6, 10, 5, '2025-02-06 11:00:00', '2025-02-06 11:20:00', 5, 3, '굿', TRUE);


-- 16. video_review
INSERT INTO video_review
(id, user_id, video_id, satisfaction, difficulty, content)
VALUES
(1, 2, 1, 5, 3, '덕분에 살 빠짐'),
(2, 3, 1, 4, 2, '가볍게 하기 좋음'),
(3, 5, 2, 3, 3, '무난무난'),
(4, 7, 3, 5, 4, '엄청 힘듦'),
(5, 8, 4, 4, 3, '좋았어요'),
(6, 10, 5, 5, 2, '최고의 루틴!');


-- 17. video_like
INSERT INTO video_like (user_id, video_id, cancelled) VALUES
(2, 1, FALSE),
(3, 1, FALSE),
(5, 2, FALSE),
(7, 3, TRUE),
(8, 4, FALSE),
(10, 5, FALSE);


-- 18 . playlist_video
INSERT INTO playlist_video (playlist_id, video_id) VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 4),
(3, 5);

-- 19. group-member 
INSERT INTO group_member (group_id, user_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 7);

INSERT INTO group_member (group_id, user_id)
VALUES
(2, 5),
(2, 8),
(2, 9),
(2, 11),
(2, 13);

INSERT INTO group_member (group_id, user_id)
VALUES
(3, 10),
(3, 14),
(3, 16),
(3, 19);

INSERT INTO group_member (group_id, user_id)
VALUES
(4, 15),
(4, 21),
(4, 22),
(4, 24),
(4, 25);

INSERT INTO group_member (group_id, user_id)
VALUES
(5, 20),
(5, 26),
(5, 27),
(5, 30);


