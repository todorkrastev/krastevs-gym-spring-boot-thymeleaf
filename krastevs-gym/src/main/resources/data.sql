-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts



-- categories
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('1', 'ABS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701051/krastevs-gym/imgs/exercises/gifs/abs_br2dbt.gif', 'Abdominal muscles play a crucial role in posture, support of the spine, balance, stability, and respiratory functions such as breathing.
#
# If you''ve been skipping your ab session, let''s start crushing your core!');
#
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('2', 'BICEPS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701049/krastevs-gym/imgs/exercises/gifs/biceps_bg9z4r.gif', 'Strong arm muscles are good for more than carrying your girlfriend''s end-of-season-sale shopping haul.
#
# If you''ve been skipping your arms day, let''s start strengthening your guns!');
#
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('3', 'CHEST',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701060/krastevs-gym/imgs/exercises/gifs/chest_olpma2.gif', 'Chest exercises are an important way to build upper body strength.
#
# Strengthening the muscles making up the chest can help you achieve a strong, sculpted physique.
#
# Let''s start training for a stronger chest!');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('4', 'FOREARMS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701061/krastevs-gym/imgs/exercises/gifs/forearms_sekwzg.gif', 'Strong arm muscles are good for more than carrying your girlfriend''s end-of-season-sale shopping haul.
#
# If you''ve been skipping your arms day, let''s start strengthening your guns!');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('5', 'LATS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701046/krastevs-gym/imgs/exercises/gifs/back_ttxpl3.gif', 'It takes a strong back to lead a strong life!
#
# In fact, a weak back is one of the most common causes of back pain and injury.
#
# Is your back strong enough?');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('6', 'LEGS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701051/krastevs-gym/imgs/exercises/gifs/legs_vv3pe0.gif', 'Leg exercises can be tough.
#
# Of all the workout days, they can get the most groans.
#
# Don''t skip leg day, prioritise it!');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('7', 'LOWER_BACK',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1721175473/krastevs-gym/imgs/exercises/gifs/lower-back_ozahle.gif', 'It takes a strong back to lead a strong life!
#
# In fact, a weak back is one of the most common causes of back pain and injury.
#
# Is your back strong enough?');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('8', 'SHOULDERS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701048/krastevs-gym/imgs/exercises/gifs/shoulders_pcctie.gif', 'The shoulders are a complex and vital joint system.
#
# Strong shoulders significantly improve upper-body strength and stability.
#
# Are you ready to slay those shoulders?');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('9', 'TRAPS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1721175591/krastevs-gym/imgs/exercises/gifs/traps_dvlc1m.gif', 'It takes a strong back to lead a strong life!
#
# In fact, a weak back is one of the most common causes of back pain and injury.
#
# Is your back strong enough?');
# INSERT INTO exercise_categories (id, category, gif_url, description)
# VALUES ('10', 'TRICEPS',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701058/krastevs-gym/imgs/exercises/gifs/triceps_uf74jh.gif', 'Strong arm muscles are good for more than carrying your girlfriend''s end-of-season-sale shopping haul.
#
# If you''ve been skipping your arms day, let''s start strengthening your guns!');
#
#
# -- exercises
# INSERT INTO exercises (name, instructions, equipment_type, category_id, gif_url,
#                        muscles_worked_url, user_id)
# VALUES ('Stability ball V-pass', 'Lie with your back flat on the floor with your legs extended straight on the floor, holding a stability ball overhead with both hands. Brace your core to minimize any arch in your lower back. This is your starting position.
# Squeeze your abs to lift your arms and legs to place the ball between your calves, creating a “V” position.
# Lower back down to the starting position, but this time with the ball between your legs.
# Repeat the movement, passing the ball back and forth between your hands and legs.', 'BODYWEIGHT', 1,
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701050/krastevs-gym/imgs/abs/gifs/stability-ball-v-pass_e9fle3.gif',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg',
#         1);
#
# INSERT INTO exercises (name, instructions, equipment_type, category_id, gif_url,
#                        muscles_worked_url, user_id)
# VALUES ('One-arm dumbbell preacher curl', 'Sit on the preacher bench with a dumbbell in your hand.
# Supinate your forearm (turn your palm upward), bend your elbow so that the dumbbell faces your shoulder, and rest your upper arm flat on the bench.
# Inhale as you lower the dumbbell until your arm is almost fully extended.
# Exhale as you curl the dumbbell to the starting position.
# Repeat for the desired number of repetitions.
# Repeat with your opposite arm.', 'DUMBBELLS', 2,
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701055/krastevs-gym/imgs/biceps/gifs/one-arm-dumbbell-preacher-curl_qu78vm.gif',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg',
#         1);
#
# INSERT INTO exercises (name, instructions, equipment_type, category_id, gif_url,
#                        muscles_worked_url, user_id)
# VALUES ('Reverse Grip Triceps Pushdown', 'Start by setting a bar attachment (straight or e-z) on a high pulley machine.
# Facing the bar attachment, grab it with the palms facing up (supinated grip) at shoulder width. Lower the bar by using your lats until your arms are fully extended by your sides. Tip: Elbows should be in by your sides and your feet should be shoulder width apart from each other. This is the starting position.
# Slowly elevate the bar attachment up as you inhale so it is aligned with your chest. Only the forearms should move and the elbows/upper arms should be stationary by your side at all times.
# Then begin to lower the cable bar back down to the original staring position while exhaling and contracting the triceps hard.
# Repeat for the recommended amount of repetitions.
# Variation: This exercise can also be performed with a single handle using one arm at a time. This will allow you to better isolate the triceps. With this version you can self spot yourself by placing your hand over your forearm and applying some pressure to help you perform more reps than before.',
#         'MACHINES', 10,
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701047/krastevs-gym/imgs/triceps/gifs/reverse-grip-triceps-pushdown_jyis9l.gif',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg',
#         1);
#
# INSERT INTO exercises (name, instructions, equipment_type, category_id, gif_url,
#                        muscles_worked_url, user_id)
# VALUES ('Triceps dip', 'Mount the should-width dip bars and straighten your arms so that your legs are suspended off the ground.
# Keeping your elbows tucked in and your body upright, slowly lower your body until your elbows form a 90-degree angle or you feel a slight stretch in your shoulders. Don’t forget to inhale.
# Exhale as you push your body back up to the starting position.
# Repeat for the recommended number of repetitions.', 'BODYWEIGHT', 10,
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701041/krastevs-gym/imgs/triceps/gifs/triceps-dip_set7r5.gif',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg',
#         1);
#
# INSERT INTO exercises (name, instructions, equipment_type, category_id, gif_url,
#                        muscles_worked_url, user_id)
# VALUES ('Dumbbell one-arm reverse wrist curl', 'Holding a dumbbell in one hand, kneel by the side of a flat bench.
# Turn your palm downward (pronate your wrist) and place your forearm flat on the bench, with your wrist and the dumbbell extending off the edge.
# Exhale as you raise the dumbbell by extending your wrist.
# Hold for a count of two.
# Inhale as you slowly lower the dumbbell to the starting position by flexing your wrist.
# Repeat for the prescribed number of repetitions.
# Repeat the exercise with your opposite arm.', 'DUMBBELLS', 4,
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719701059/krastevs-gym/imgs/forearms/gifs/dumbbell-one-arm-reverse-wrist-curl_t2tmqw.gif',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1719702627/krastevs-gym/imgs/muscles/view_of_muscles_kfthdn.jpg',
#         1);



# INSERT INTO users_exercises
# VALUES (3, 2);
# INSERT INTO users_exercises
# VALUES (3, 3);
# INSERT INTO users_exercises
# VALUES (3, 5);


-- activities
# INSERT INTO activities (id, title, description, image_url)
# VALUES (1, 'Free Weights', 'If you suffer from acute iron deficiency, you''ve come to the right place. In the Free Weights Area, you can
#           let off steam on the gym80 Olympia Premium barbells or dumbbells up to 150 kg.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/weights_otkv2w.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (2, 'Machines', 'Indestructible, incomparable and incredibly effective: With the ultimate equipment from gym80 "The King of
#           Machines" you train all muscles absolutely precisely thanks to the excellent biomechanics.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648981/krastevs-gym/imgs/home/machines_r8v6au.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (3, 'Cardio Area', 'Take your endurance to the next level with top equipment from Precor and Matrix and look forward to a gigantic
#           selection - in Constance even between 10 meter high trees!',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/cardio_e8p7uu.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (4, 'Boxing', 'You won''t meet Wladimir Klitschko or Alexander Povetkin in our gym, but the fun in our boxing classes is
#           guaranteed.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/boxing_enbvlj.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (5, 'PT & Coaching', 'You can''t get ahead and always have an excuse? Then it''s high time for the best personal training ever!
#           Experience individual 1:1 support, pure motivation, and maximum effectiveness.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/coaching_wixxkv.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (6, 'Yoga', 'If you are looking for peace of mind and solitude, come and join our yoga classes.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_j7dugv.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (7, 'VR Training & More', 'At Krastev''s Gym, we offer you two strong fitness class formats: VR Workouts and Live Classes. All classes
#           are free and suitable for all levels.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/vr_t8ozjs.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (8, 'Posing Room', 'First pump, then pose. In the fully mirrored room in Constance, that''s no problem. Because we have not spared
#           the anabolic light here, you can put your body optimally illuminated in the scene.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648984/krastevs-gym/imgs/home/posing_eowtqn.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (9, 'Sauna & Recovery',
#         'If you work hard, you can also relax. Ideally in our separate men''s and women''s sauna. Time to relax.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648985/krastevs-gym/imgs/home/sauna_hldgsa.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (10, 'Swimming Pool', 'Krastev''s Gym Fullerton features an outdoor pool and indoor hot tub, open year-round. Swim your way to better
#           health and stay cool in the Constance sun with our unlimited membership.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648983/krastevs-gym/imgs/home/pool_fzwsxw.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (11, 'Krastev''s Gym Nutrition', 'When you train, you get your body in top shape. But you also demand a lot from it. Krastev''s Gym Nutrition
#           ensures that your body is supplied with everything it needs before, during and after your workout.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648983/krastevs-gym/imgs/home/nutrition_jzewte.jpg');
#
# INSERT INTO activities (id, title, description, image_url)
# VALUES (12, 'Krastev''s Gym Shop', 'At Krastev''s Gym you have a wide selection of products specially selected for you by us. Useful fitness
#           accessories, and the perfect clothes for your workout. You can pick up your order in your Krastev''s Gym, or
#           have it conveniently delivered to your home.',
#         'https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648986/krastevs-gym/imgs/home/shop_l5ilbv.jpg');
