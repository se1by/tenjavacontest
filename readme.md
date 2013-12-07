Don't waste my time
=================
This plugin works with the scenerio shown in the movie (or the screenplay, to fit the theme :P) "In Time".
If you're familiar with it, you can skip the whole next paragraph, as it just describes their world.

Background info
==============
In 2169, humans got a digital clock on their forearm. It shows them exactly one year until they turn 25.
They stop aging, but their clock runs down. Once it reaches zero, they die. To survive, you must earn time,
it has become a currency. The poor ones die young, the rich are immortal.
Will is a 28 years old worker in the poorest district of this cruel work, he hasn't seen more than one single
day on his clock in years. He is a good guy though and saves the suicidal Henry. Henry is 105 years old and
tells him that his mind is spent while his body isn't. He wants to die. While Will is asleep, Henry transfers
his 110 years (an incredible amount of money/time for that district) to him and goes away to die, leaving a message
for Will:

##Don't waste my time

If you want to know the end, watch the film (it's really worth it!).

Back to my plugin
===============
My plugin uses the principle "time is money". simple as that.
A new user gets one ingame year, which is about 5 real life days, enough to find a way to earn more time.
With later help of vault, you could make money through all kinds of shops etc, but I was asked to leave it out in this contest.
Another way to make money is gambling.
You can dare another user to gamble with you, even to death. In the movie, gambling works through arm wrestling.
In my plugin, it works slightly different :P
A conversation api opens, and you are asked to type in one of 10 random words. If you type it in correct, you get "the upper hand",
meaning that you get time from your opponent (loss rate configurable, more about that later). When he enters a word correct, you
lose time. The game ends when one of both enters "abort", or one of them is dead. When started with the argument "death", you can't
abort.

Config stuff
===========
* time_manager: yaml //You can choose between yaml and mysql to save the times of your users, but mysql isn't implemented yet due to the time
* counter_interval: 200 //How often the plugin checks and deducts bygone time on the servers.
* out_of_time_behaviour: BAN //What the plugin should do with people which time ran out. BAN, KICK, KILL
* kick_message: Your time has come... //I case you decide to kick them, which message should be shown?
* fight_time_loss: 30 //How much seconds should they lose every tick in a fight?

out_of_time_behaviour will trigger every lower action, e.g. a KICK also KILL the player.

Commands
========
* /intime //the basic command, everything else is an argument to that command
⋅⋅⋅* transfer \[name\] \[amount\] pretty much self explaining, isn't it. User needs permission "DWMT.transfer".
⋅⋅⋅* gettime \[name\] get the clock of themself (without \[name\]), or another player (another player needs "DWMT.see" for that)
⋅⋅⋅* settime \[name\] \[amount\] sets a players time to \[amount\], needs "DWMT.admin"
⋅⋅⋅* addtime \[name\] \[amount\] adds \[amount\] to players time, needs "DWMT.admin"
⋅⋅⋅* dare \[name/accept|decline\] \[death\] dare another player to fight, he can accept or decline it. write death to remove the abort code, needs "DWMT.dare"
⋅⋅⋅* kitten meow :3
That's it!
==========
If I forgot something, just ask
