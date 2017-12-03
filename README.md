# CampusPin
Our app aims to help users to find out the name and address of a building in the uploaded picture. Also, it can show you the activities going on inside the building or useful resources like printers or bathrooms around. The picture to upload can be either you find in your friends' twitter, facebook, or the one you took when you are passing by a memorable building.
## Android App
For now, we can realize almost all the basic functions in our android app now, involving Auth, Database, Storage, Analytics, etc. using Firebase.

1.Login. Users are allow to login with google account, or using email/password method. After logged in, we will update our database to change your username from "anonymous" to your nickname of Google account or the one you selected. If you are detected not being logged in, the log-in activity will show up to force you to login. Otherwise you can't use the app anymore.

2.Search function. When you are trying to find the information of a given building, like for example, you want to search for "Photonics Center", you can simply type "Photonics Center" in the textbox, and press "search", then it will show you all the information you need. For now, we only show the address of the building. Our database is still quite simple and small, but since we can already retrieve value from our database, it won't be a problem if you want to show more in a more decent way. By the way, we can also track users' searching history for further use.

3.Upload function. When you press the "Choosing pictures" button in the middle, it firstly opens photo galary in your local position. After choosing one, and press "ok", it will be uploaded to the Firebase storage, then automatically giving back the actual url of the picture and update it into our database.  

## Recognition


### App DEMO

https://youtu.be/mnh9CaRLNgk

https://youtu.be/WPbxhzRGw3E

Description is as above.


## Running the tests

https://youtu.be/l7IX_EygBNM

Several tests were performed on the recognition module, and behaved basicly as we expect that each test matched exactly the correct building it should be. 
In the single_test video, firstly showed the path to train file which is named tf_file. 
All the train set are stored inside, there are 3 train classes, Marsh Chapel(chapel), Photonics Center(pho) and Agganis Arena(aa). 
Then, open the test file, test 3 pictures-test1.jpg, test2.jpg and test3.jpg separately. The module outputs the percentage of the similarity of each building. The one gives the highest percentage of similarity is what we are looking for. 

## Current Challenges

1.The size of our training set is still too small, so the precision of recognition is not high enough. The difficulty is to how to get enough pictures.

2.For the building information part of database, we need to enrich the information of our existing buildings. We now have the building name, address, resources, and activities. We may want to add more properties for each building.

3.Allow users to add events to a given building. For example, a career fair or a costume party.

4.If the picture that user uploads don't match any of the buildings in the database, we may allow user to create new building into our database.

5.For the 5 test recognition, when the number of the image is over 30, the module crashed. We are still trying to ﬁgure out what’s going on inside.

6.Connect the recognition module into the android app.

7.The picture that users upload should be used to enrich our training set for recognition module. But only if the numbers of newly uploaded pictures reach a given number, or a given percentage of all pictures in the training set. This is avoid doing too much repetitive job every time when a picture being uploaded.

## Built With

* [Android Studio](https://developer.android.com/studio/index.html) - Android App used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Tenser Flow](https://www.tensorflow.org/) - Used to train recognition module

## Authors

* **Jialiang Shi** 
* **Mingzi Cao** 
* **Zhiyu Wang** 

See also the list of [contributors](https://github.com/mingzicao/601-BSRAPP-CampusPin/graphs/contributors) who participated in this project.
