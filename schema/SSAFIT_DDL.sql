
DROP DATABASE IF EXISTS SSAFIT;

CREATE DATABASE IF NOT EXISTS SSAFIT
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
USE SSAFIT;

-- 1. 운동 목표 유형
CREATE TABLE goal_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) UNIQUE NOT NULL,
    description TEXT
);

-- 2. 운동 그룹 (외래키 없이)
CREATE TABLE workout_group (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100) NOT NULL,
    master_id BIGINT NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE
);


-- 3. 회원
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    login_id VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    pwd VARCHAR(255),
    role VARCHAR(10),
    profile_img_url VARCHAR(255),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    nickname VARCHAR(30) UNIQUE,
    age INT,
    birthdate DATE,
    gender CHAR(1),
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    height DECIMAL(4,1),
    weight DECIMAL(4,1),
    goal_type BIGINT,
    streak_days INT DEFAULT 0,
    max_streak_days INT DEFAULT 0,
    total_workout_days INT DEFAULT 0,
    total_workout_minutes INT DEFAULT 0,
    total_calories INT DEFAULT 0,
    level BIGINT DEFAULT 0,
    exp BIGINT DEFAULT 0,
    coin BIGINT DEFAULT 0,
    group_id BIGINT NULL,
    marketing_agree BOOLEAN,
    active BOOLEAN DEFAULT TRUE,
    update_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--     CONSTRAINT fk_user_group FOREIGN KEY (group_id) REFERENCES workout_group(id),
    CONSTRAINT fk_user_goal FOREIGN KEY (goal_type) REFERENCES goal_type(id)
);

-- 2-2) user 테이블 생성 후 외래키 추가
ALTER TABLE workout_group
ADD CONSTRAINT fk_workout_group_master
FOREIGN KEY (master_id) REFERENCES user(id);

-- 4. 팔로우-팔로잉
CREATE TABLE follow (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    follower_id BIGINT NOT NULL,
    followee_id BIGINT NOT NULL,
    follow_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uq_follower_followee (follower_id, followee_id),
	CONSTRAINT fk_follow_follower FOREIGN KEY (follower_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_follow_followee FOREIGN KEY (followee_id) REFERENCES user(id) ON DELETE CASCADE
);

-- 5. 운동 영상
CREATE TABLE video (
    id BIGINT PRIMARY KEY,
    title VARCHAR(200),
    description TEXT,
    video_url VARCHAR(255),
    thumbnail_url VARCHAR(255),
    duration_seconds BIGINT,
    difficulty INT,
    target_part VARCHAR(30),
    calorie_estimate INT,
    category VARCHAR(50),
    view_cnt BIGINT,
    like_cnt BIGINT,
    review_cnt BIGINT,
    created_by BIGINT,
    created_at DATETIME,
    updated_at DATETIME,
    visibility BOOLEAN DEFAULT TRUE,
    is_deleted BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_video_admin FOREIGN KEY (created_by) REFERENCES user(id)
);

-- 6. 운동 기록
CREATE TABLE workout_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    video_id BIGINT NOT NULL,
    start_time DATETIME,
    end_time DATETIME,
    user_satisfaction INT,
    personal_difficulty INT,
    user_memo TEXT,
    is_success BOOLEAN,
    CONSTRAINT fk_record_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_record_video FOREIGN KEY (video_id) REFERENCES video(id)
);

-- 7. 운동 영상 태그
CREATE TABLE video_tag (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tag VARCHAR(100)
);

-- 8. 영상 - 태그 관계
CREATE TABLE video_tag_map (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    video_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    CONSTRAINT fk_vtm_video FOREIGN KEY (video_id) REFERENCES video(id),
    CONSTRAINT fk_vtm_tag FOREIGN KEY (tag_id) REFERENCES video_tag(id)
);

-- 9. 운동 도구 목록
CREATE TABLE workout_tool (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    tool_name VARCHAR(100)
);

-- 10. 운동 영상 별 추천 도구
CREATE TABLE video_tool_map (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    video_id BIGINT NOT NULL,
    tool_id BIGINT NOT NULL,
    CONSTRAINT fk_vtm_tool_video FOREIGN KEY (video_id) REFERENCES video(id),
    CONSTRAINT fk_vtm_tool FOREIGN KEY (tool_id) REFERENCES workout_tool(id)
);

-- 11. 운동 영상 리뷰
CREATE TABLE video_review (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    video_id BIGINT NOT NULL,
    satisfaction INT,
    difficulty INT,
    content TEXT,
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_review_video FOREIGN KEY (video_id) REFERENCES video(id)
);


-- 12. 영상 찜
CREATE TABLE video_like (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    video_id BIGINT NOT NULL,
    like_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    cancelled BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_review_like_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_review_like_video FOREIGN KEY (video_id) REFERENCES video(id)
);

-- 13. 운동 플레이리스트
CREATE TABLE playlist (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    publisher_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    CONSTRAINT fk_playlist_publisher FOREIGN KEY (publisher_id) REFERENCES user(id)
);


-- 14. 영상-플레이리스트 관계
CREATE TABLE playlist_video (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    playlist_id BIGINT NOT NULL,
    video_id BIGINT NOT NULL,
    reg_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_playlist_video_playlist FOREIGN KEY (playlist_id) REFERENCES playlist(id),
    CONSTRAINT fk_playlist_video_video FOREIGN KEY (video_id) REFERENCES video(id)
);

-- 15. 미션/챌린지/업적
CREATE TABLE mission (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mission_name VARCHAR(100),
    description TEXT
);

-- 16. 미션/챌린지/업적 달성 기록
CREATE TABLE mission_record (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mission_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    achieved_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mission_record_mission FOREIGN KEY (mission_id) REFERENCES mission(id),
    CONSTRAINT fk_mission_record_user FOREIGN KEY (user_id) REFERENCES user(id)
);

-- 17. 게시글 카테고리
CREATE TABLE post_category (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(100)
);

-- 18. 자유게시글
CREATE TABLE post (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    category BIGINT,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    image_urls TEXT,
    view_cnt BIGINT DEFAULT 0,
    like_cnt BIGINT DEFAULT 0,
    comment_cnt INT DEFAULT 0,
    is_pinned BOOLEAN DEFAULT FALSE,
    is_notice BOOLEAN DEFAULT FALSE,
    visibility BOOLEAN DEFAULT TRUE,
    deleted BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_member  FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_post_category FOREIGN KEY (category) REFERENCES post_category(id)
);



-- 19. 댓글
CREATE TABLE comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    like_cnt BIGINT DEFAULT 0,
    deleted BOOLEAN DEFAULT FALSE,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES post(id),
	CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id)
);

  
-- 20. 그룹-회원 관계 
CREATE TABLE group_member (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    joined_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    CONSTRAINT fk_group_member_group FOREIGN KEY (group_id) REFERENCES workout_group(id),
    CONSTRAINT fk_group_member_user FOREIGN KEY (user_id) REFERENCES user(id),
    UNIQUE (group_id, user_id)
);

-- 21. post 테이블에 group_id 추가
ALTER TABLE post
ADD COLUMN group_id BIGINT NULL,
ADD CONSTRAINT fk_post_group
FOREIGN KEY (group_id) REFERENCES workout_group(id);

-- 22. 로그아웃을 위해 user 테이블 수정 (토큰 버전 추가)
ALTER TABLE user
ADD COLUMN token_version INT NOT NULL DEFAULT 0;
