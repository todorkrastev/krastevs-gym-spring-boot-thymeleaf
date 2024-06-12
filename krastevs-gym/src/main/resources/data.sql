-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts

-- user roles
INSERT INTO roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, name)
VALUES (2, 'MODERATOR');
INSERT INTO roles (id, name)
VALUES (3, 'USER');

-- some test users
INSERT INTO users (id, age, email, name, level, password, weight, height)
VALUES (1, 28, 'admin@admin.com', 'Admin Adminov', 'ADVANCED',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 80, 183);

INSERT INTO users (id, age, email, name, level, password, weight, height)
VALUES (2, 29, 'moderator@moderator.com', 'Moderator Moderatorov', 'INTERMEDIATE',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 70, 172);

INSERT INTO users (id, age, email, name, level, password, weight, height)
VALUES (3, 30, 'user@user.com', 'User Userov', 'BEGINNER',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 60, 161);

INSERT INTO users (id, age, email, name, level, password, weight, height)
VALUES (4, 33, 'ivan@ivan.com', 'Ivan Ivanov', 'BEGINNER',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 90, 194);
-- user roles
-- admin
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (1, 3);
-- moderator
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (2, 2);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (2, 3);
-- user
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (3, 3);
-- user 2
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (4, 3);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (4, 2);
INSERT INTO users_roles (`user_entity_id`, `roles_id`)
VALUES (4, 1);


-- posts
INSERT INTO posts (title, content, author_id)
VALUES ('Supplements: What You Really Need?', 'Vitamin D
It helps keep your bones strong. People who have healthy levels of it may be less likely to get certain conditions, but more research is needed. Your body makes vitamin D when you’re in sunshine. It’s also in salmon, tuna, and fortified foods. If you’re low on vitamin D, your doctor may suggest a supplement. But several large studies show no benefits to otherwise healthy adults. And taking too much is bad for you.',
        1);

INSERT INTO posts (title, content, author_id)
VALUES ('How Often Should You Work Out?', 'How often you should work out depends on several factors, like your activity level, age, fitness goals, and more. If you are new to working out, you may wonder, "How long do I need to exercise?" and "Is working out three days enough?"
Generally, experts advise 150 minutes of moderate physical activity per week or 30 minutes daily, five days per week. You can also meet your fitness goals by doing 75 minutes of vigorous physical activity or 25 minutes daily, three days per week.1',
        2);

INSERT INTO posts (title, content, author_id)
VALUES ('What are the laws regarding steroids in Germany?',
        'In Germany, the use and possession of anabolic steroids without a prescription is illegal. Anabolic steroids are classified as controlled substances, and their unauthorized use can lead to legal consequences. It''s important to consult with a medical professional or legal advisor for specific information regarding steroids in Germany.',
        2);

INSERT INTO posts (title, content, author_id)
VALUES ('Can I take magnesium and zinc together?', 'Yes!

All types of vitamins and minerals are in competition, in that they need the right conditions for your body to absorb them properly. Some minerals have mutual benefits, while some only counteract each other’s level of absorption (known as bioavailability).

Taken together, and in the right dosages, magnesium and zinc work to each other’s advantage. In fact, they do this so well that you’ll find a lot of oral supplements (tablets, capsules etc.) on the market that combine the two.

Magnesium helps your body regulate its zinc levels, though it should be noted that high intake of zinc can be detrimental to magnesium absorption and reduce magnesium balance - it will only hinder absorption when taken in abnormally high doses (around 142 mg of zinc per day).',
        4);


-- comments
INSERT INTO comments (`approved`, `created`, `content`, `author_id`, `post_id`)
VALUES (1, '2021-11-14 08:10:40', X'457874726120636F6F6C20726F75746521', 1, 1),
       (0, '2021-11-14 08:10:40', X'496E6372656469626C65', 1, 1);

-- exercises
INSERT INTO exercises (`exercise_name`, `instructions`, `equipment_type`)
VALUES ('Stability ball V-pass', 'Lie with your back flat on the floor with your legs extended straight on the floor, holding a stability ball overhead with both hands. Brace your core to minimize any arch in your lower back. This is your starting position.
Squeeze your abs to lift your arms and legs to place the ball between your calves, creating a “V” position.
Lower back down to the starting position, but this time with the ball between your legs.
Repeat the movement, passing the ball back and forth between your hands and legs.', 'BODYWEIGHT');

INSERT INTO exercises (`exercise_name`, `instructions`, `equipment_type`)
VALUES ('One-arm dumbbell preacher curl', 'Sit on the preacher bench with a dumbbell in your hand.
Supinate your forearm (turn your palm upward), bend your elbow so that the dumbbell faces your shoulder, and rest your upper arm flat on the bench.
Inhale as you lower the dumbbell until your arm is almost fully extended.
Exhale as you curl the dumbbell to the starting position.
Repeat for the desired number of repetitions.
Repeat with your opposite arm.', 'DUMBBELLS');

INSERT INTO exercises (`exercise_name`, `instructions`, `equipment_type`)
VALUES ('Dumbbell one-arm reverse wrist curl', 'Holding a dumbbell in one hand, kneel by the side of a flat bench.
Turn your palm downward (pronate your wrist) and place your forearm flat on the bench, with your wrist and the dumbbell extending off the edge.
Exhale as you raise the dumbbell by extending your wrist.
Hold for a count of two.
Inhale as you slowly lower the dumbbell to the starting position by flexing your wrist.
Repeat for the prescribed number of repetitions.
Repeat the exercise with your opposite arm.', 'DUMBBELLS');

INSERT INTO exercises (`exercise_name`, `instructions`, `equipment_type`)
VALUES ('Reverse Grip Triceps Pushdown', 'Start by setting a bar attachment (straight or e-z) on a high pulley machine.
Facing the bar attachment, grab it with the palms facing up (supinated grip) at shoulder width. Lower the bar by using your lats until your arms are fully extended by your sides. Tip: Elbows should be in by your sides and your feet should be shoulder width apart from each other. This is the starting position.
Slowly elevate the bar attachment up as you inhale so it is aligned with your chest. Only the forearms should move and the elbows/upper arms should be stationary by your side at all times.
Then begin to lower the cable bar back down to the original staring position while exhaling and contracting the triceps hard.
Repeat for the recommended amount of repetitions.
Variation: This exercise can also be performed with a single handle using one arm at a time. This will allow you to better isolate the triceps. With this version you can self spot yourself by placing your hand over your forearm and applying some pressure to help you perform more reps than before.', 'MACHINES');

INSERT INTO exercises (`exercise_name`, `instructions`, `equipment_type`)
VALUES ('Triceps dip', 'Mount the should-width dip bars and straighten your arms so that your legs are suspended off the ground.
Keeping your elbows tucked in and your body upright, slowly lower your body until your elbows form a 90-degree angle or you feel a slight stretch in your shoulders. Don’t forget to inhale.
Exhale as you push your body back up to the starting position.
Repeat for the recommended number of repetitions.', 'BODYWEIGHT');

-- categories
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('1', 'ABS');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('2', 'BACK');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('3', 'BICEPS');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('4', 'CHEST');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('5', 'FOREARMS');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('6', 'LEGS');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('7', 'SHOULDERS');
INSERT INTO exercise_categories (id, exercise_category)
VALUES ('8', 'TRICEPS');

INSERT INTO exercises_exercise_categories
VALUES (1, 1);
INSERT INTO exercises_exercise_categories
VALUES (2, 3);
INSERT INTO exercises_exercise_categories
VALUES (3, 1);
INSERT INTO exercises_exercise_categories
VALUES (4, 2);
INSERT INTO exercises_exercise_categories
VALUES (5, 4);
INSERT INTO exercises_exercise_categories
VALUES (5, 8);

INSERT INTO users_exercises
VALUES (3, 2);
INSERT INTO users_exercises
VALUES (3, 3);
INSERT INTO users_exercises
VALUES (3, 5);

-- pictures
INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Kumata', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg', 1, 1);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Kumata', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg', 1, 1);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Velo Erul', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630581418/tqhjrinmsb69ev7upg0q.jpg', 1, 2);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Velo Erul 2', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630582448/oowojgn4lagybkvv20jb.jpg', 1, 2);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Aleko', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630582596/tclvroyrkcfbz98yklmc.jpg', 1, 3);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Aleko 2', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630582767/ukdmkxyvlnani3hzaafi.jpg', 1, 3);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Shipka', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630583376/en6lxhaddbvhf1ksybbu.jpg', 1, 4);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Shipka 2', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630583467/koncyanx4gqwzt9vxgx4.jpg', 1, 4);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Aladja Manastir', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630583842/qabnwpgq3pp1cui19nn5.jpg', 1,
        5);

INSERT INTO pictures(title, url, author_id, exercise_id)
VALUES ('Aladja Manastir 2', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630584095/aznlgfzoxpuvl5belukb.jpg', 1,
        5);


