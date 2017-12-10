# CampusPin
Our app aims to help users to find out the name and address of a building in the uploaded picture. Also, it can show you the activities going on inside the building or useful resources like printers or bathrooms around. 

## DEMO

https://youtu.be/mnh9CaRLNgk

https://youtu.be/WPbxhzRGw3E

## Features
1.Login/Signup/Logout

Users are allow to login with google account or email/password method. After logged in, we will update our database to change your username from "Anonymous" to your nickname of Google account or the one you selected. You can also sign up account if you are new user. Log out if you want to change account or simply want to stop using the app.

2.Search Bar

When you try to find some information of a given building, for example, you want to know if there is printer inside Photonics Center, you can simply type "Photonics Center" in the textbox, and press "search". Then it will show you all the information you need. We can also track users' searching history for further use. Also we have simple spelling correction if you have wrong input.

3.Search by Photo

The picture to upload can be either you find in your friends' twitter, facebook, or the one you took when you passing by a memorable building, which you want to know something about it. 

You can simply press the "Choosing pictures" button, it firstly opens photo galary in your local position. After choosing one, and press "ok", it will be uploaded to the Firebase storage, then automatically giving back the actual url of the picture and update into our database.  After 5-10 seconds, you will see the information of the most similar building as you uploaded. If all the similarity rate is under certain number, you will get a "No Matching" message. Then you can choose to create a new building into our database by entering basic information about this building.

4.Search History(under development)

You can review all of your search history with brief information and a preview picture. By clicking it, all information will show up.

5.My Favorite(under development)

You can add buildings into your Favorite List by click the red heard in the information page.

## Recognition
Most recent update of recognition is in the master branch. It includes all the files which could run in AWS, run in terminal. Just follow the readme inside, you can run the recognition module in different cases.

## Tests for Recognition Module

https://youtu.be/l7IX_EygBNM

Several tests were performed on the recognition module, and behaved basicly as we expect that each test matched exactly the correct building it should be. 

In the single_test video, we firstly showed the path to train file which is named tf_file. 

All the train set are stored inside. There are 3 training classes, Marsh Chapel(chapel), Photonics Center(pho) and Agganis Arena(aa). 
Then, we open the test file, test 3 pictures-test1.jpg, test2.jpg and test3.jpg separately. The module outputs the percentage of the similarity of each building. The one gives the highest percentage of similarity is what we are looking for. 

## Current Challenges

1.Enlarge the size of our training set using the uploaded pictures

2.Enrich the information of our existing buildings by allowing users to add events to a given building

3.Allow users to create new building into our database

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Tenser Flow](https://www.tensorflow.org/) - Used to train recognition module
* [Firebase](https://firebase.google.com/) - Used for Auth, Database, Storage and Analytics


## Authors

* **Jialiang Shi** 
* **Mingzi Cao** 
* **Zhiyu Wang** 

See also the list of [contributors](https://github.com/mingzicao/601-BSRAPP-CampusPin/graphs/contributors) who participated in this project.
