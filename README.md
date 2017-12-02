# 601-BSRAPP-CampusPin
# Accomplishments
1.master branch

We are trying to build an app that basically helps users to find out the exact name and address of a given picture, it can also show you the current activities happening in this building or useful resources like printers or bathrooms around. 
The picture can be either what you find in your friends' twitter, facebook or other social networks, or the one you took from when you are passing by a beautiful and memorable building that you really want to know what its name is or what's going on inside of this building.
For now, we can realize almost all the basic functions in our android app now, involving Auth, Database, Storage, Analytics, etc. using Firebase.
  1)Login. Users are allow to login with google account, or using email/password method. After logged in, we will update our database to change your username from "anonymous" to your nickname of Google account or the one you selected. If you are detected not being logged in, the log-in activity will show up to force you to login. Otherwise you can't use the app anymore.
  2)Search function. When you are trying to find the information of a given building, like for example, you want to search for "Photonics Center", you can simply type "Photonics Center" in the textbox, and press "search", then it will show you all the information you need. For now, we only show the address of the building. Our database is still quite simple and small, but since we can already retrieve value from our database, it won't be a problem if you want to show more in a more decent way. By the way, we can also track users' searching history for further use.
  3)Upload function. When you press the "Choosing pictures" button in the middle, it firstly opens photo galary in your local position. After choosing one, and press "ok", it will be uploaded to the Firebase storage, then automatically giving back the actual url of the picture and update it into our database.  

2.tree/recognition

We also finished the training part of our recognition module, and did several tests, which behaved exactly as we expect, and matched exactly the correct building it should be. 

# DEMO
1) Single recognition test
https://youtu.be/l7IX_EygBNM
In the single_test video, first, I showed the path to train file which is named tf_file. 
All the train set are stored inside, there are 3 train classes, chapel, pho and aa. I showed all the training data set. 
Then I opened the test file, in this video, I will test these 3 test picture separately to find out if our module is working. You can see the test1.jpg is chapel, the test2 is Pho, and the test3 is aa. 
Now lets run our module. 
I feed in the module with the test1.jpg, the module outputs the percentage of the similarity. And the prediction is correct, it is class1 chapel. 
Similarly, I also test the test2 and test3, they also provided the right prediction. So, for the single test part, the module works pretty well.

2) Multiple recognition test
https://youtu.be/PfREll1pICA
The 5 test demo Video showed the test set which consists of 5 pictures and our module got all them correctly.
And for the total test set, it needs about 40 minutes to accomplish. And in the picture I showed another 20 test set result.

3) App

https://youtu.be/mnh9CaRLNgk

https://youtu.be/WPbxhzRGw3E

Description is as above.


# Challenges

1.The size of our training set is still too small, so the precision of recognition is not high enough. The difficulty is to how to get enough pictures.

2.For the building information part of database, we need to enrich the information of our existing buildings. We now have the building name, address, resources, and activities. We may want to add more properties for each building.

3.Allow users to add events to a given building. For example, a career fair or a costume party.

4.If the picture that user uploads don't match any of the buildings in the database, we may allow user to create new building into our database.

5.For the 5 test recognition, when the number of the image is over 30, the module crashed. We are still trying to ﬁgure out what’s going on inside.

6.We need to connect the recognition module into our system.

7.The picture that users upload should be used to enrich our training set for recognition module. But only if the numbers of newly uploaded pictures reach a given number, or a given percentage of all pictures in the training set. This is avoid doing too much repetitive job every time when a picture being uploaded.

