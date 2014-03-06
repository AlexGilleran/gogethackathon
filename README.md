GoGet Carbon Counter
====================

This is the code for my entry into the [2014 GeoNext/GoGet Hack Contest](http://www.geonext.com.au/hack-contest/). You can see what I entered at [http://goget.alexgilleran.com](http://goget.alexgilleran.com). In this contest, you were given access to a bunch of data covering half of GoGet's pods for roughly the month of October 2013, and told to do whatever you wanted with it.

The most exciting data was obviously the GPS tracking, but unfortunately the coordinates were of dubious accuracy, and worse still the cars seemed to phone home at completely random intervals, making comprehensive tracking of driver behaviour pretty futile.

The Idea
--------
Att the kick-off session one of the GoGet guys mentioned that they'd really like to get more electric cars into the fleet, but that Victoria had most of them because the NSW government wanted $30,000 per electric pod (with charging facilities, etc.) which was more than they could afford. I had data for the kms driven during each booking at each pod, and the model of each car that drove that booking - by mixing in some fuel consumption / emissions data, I could determine exactly how much carbon / fuel could be saved by converting each pod to electric.

The Implementation
------------------
At roughly the same time I accepted a job offer from Smart Sparrow, so wanting to get familiar with their stack became a factor for choice of technologies, but at the same time I only had three weeks and I didn't want to spend every waking hour. The result was a mix of stuff I knew and stuff I wanted to learn.

The original implementation as entered into the contest was:
### Backend
  - Java
  - Spring
  - Jersey
  - SuperCSV (for parsing)
  - Gradle

### Frontend
  - Backbone
  - Bootstrap
  - HERE Maps (hack contest sponsor)
  - Grunt
  - Bower
  - Browserify

If you look at the code you'll notice I really took the _hack_ part of HackContest to heart. Basically the backend just scooped up the CSV values, glued them together based on ID and manually performed sorting every time a request was made. But hey, it was simple, it worked, and didn't result in too many 3am bedtimes!

The Result
----------
No dice :-(. I was one of 8 finalists who presented at the GeoNext conference, and there were some really strong entries. One guy even managed to fix GoGet's awful GPS tracks, but like me got no love from the judges. But it's not about winning, it's what you learn along the way!

If that positive attitude didn't fool you then you're wise indeed - I actually feel surprisingly bitter about the whole thing. I do wonder if it'd be better to have less of a prize so it becomes more about what you create. Ah well ;-).

The Aftermath
-------------
As mentioned, I'd just signed the contract for Smart Sparrow and there were a few more things I wanted to learn - so why not modify this?

They were:
  - Apache Thrift
  - MySQL
  - MyBatis

The result is that data access is done using MyBatis but it still works exactly the same as it did when it was read into memory from CSVs. Obviously it'd be better to use the database to do sorting etc, but I just wanted to learn how to use it and this did that job.

Thrift similarly serves very little useful purpose - it works exactly the same way as the Jersey service did, and because it's being called via AJAX from the web app all serialization is done to Thrift JSON anyway - which in my testing actually results in a _bigger_ response size once gzipped.

But it is pretty cool technology.

Notes
-----
The code is here for giggles, you won't actually be able to run this because it's based on GoGet's data, which I had to sign an NDA to use and hence can't just put up on GitHub for anyone to see.