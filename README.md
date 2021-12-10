# CEMbot
Just an average bot, playing songs to self isolate to.

## Running the bot
In ```Melody``` class you need to put your bot token to get it to work.
Edit ```songs.yaml``` to add more songs or remove them.

---

## Available Commands
- !melody join
  - Connects to the channel of the user who executed the command.
- !melody playing
  - Shows current name and link of the song

---

## How does it work?

You connect to the voice channel, run join command and bot will automatically start playing music.
It will stop after all users in the channel leave and currently played song ends.
Bot will start playing again after someone joins the channel again.
Songs are randomly shuffled.
