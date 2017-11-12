#include <iostream>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/core/core.hpp>
#include <opencv2/nonfree/features2d.hpp>
#include <opencv2/calib3d/calib3d.hpp>

using namespace cv;
using namespace std;

int main(){
    //Step1.get all the imgs.
    Mat image0 = imread("/Users/zhiyuwang/Desktop/EC601/opencvXtest/opencvXtest/pho1.jpg");
    Mat image1 = imread("/Users/zhiyuwang/Desktop/EC601/opencvXtest/opencvXtest/chapel/chapel4.jpeg");
    Mat image2 = imread("/Users/zhiyuwang/Desktop/EC601/opencvXtest/opencvXtest/pho/pho3.jpg");
    
    resize(image0, image0, Size(image0.cols/3, image0.rows/3));
    resize(image1, image1, Size(image1.cols/3, image1.rows/3));
    resize(image2, image2, Size(image2.cols/3, image2.rows/3));
    
    //Step2.detect their corner featureswith surf algorithm
    vector<KeyPoint> keypoints0,keypoints1;
    vector<KeyPoint> keypoints2;
    SurfFeatureDetector detector(500);
    detector.detect(image0,keypoints0);
    detector.detect(image1,keypoints1);
    detector.detect(image2,keypoints2);
    Mat img_keypoints_0,img_keypoints_1,img_keypoints_2;
    
    SurfDescriptorExtractor surfDesc;
    Mat descriptros0,descriptros1,descriptros2;
    surfDesc.compute(image1,keypoints1,descriptros1);
    surfDesc.compute(image0,keypoints0,descriptros0);
    surfDesc.compute(image0,keypoints2,descriptros2);//pho
    //double dist_ham01 = norm(descriptros0,descriptros1,NORM_L2);
    //double dist_ham02 = norm(descriptros0,descriptros2,NORM_L2);
    //cout << "dist_ham01:" << dist_ham01 << endl;
    //cout << "dist_ham02:" << dist_ham02 << endl;
    //cout << descriptros0 << endl;
///////////////////////////////
    //step3. making a feature matching.
    BFMatcher matcher;
    vector<DMatch> matches01,matches02;
    matcher.match(descriptros0, descriptros1, matches01);
    matcher.match(descriptros0, descriptros2, matches02);

    std::nth_element(matches01.begin(),matches01.begin()+24,matches01.end());
    matches01.erase(matches01.begin()+25,matches01.end());
    double sum01 = 0;
    double distave01 = 0;
    for(int i = 0;i<25;i++){
        sum01 = sum01 + matches01[i].distance;
    }
    distave01 = sum01/25;
    cout << "average distance01:" << distave01 <<endl;
    ////////////
    std::nth_element(matches02.begin(),matches02.begin()+24,matches02.end());
    matches02.erase(matches02.begin()+25,matches02.end());
    double sum02 = 0;
    double distave02 = 0;
    for(int i = 0;i<25;i++){
        sum02 = sum02 + matches02[i].distance;
    }
    distave02 = sum02/25;
    cout << "average distance02:" << distave02 <<endl;
    ////////////
    
    if((distave01<distave02)&&(distave01<0.15))
        cout << "it is chapel" <<endl;
    else{
        cout << "it is not chapel" << endl;
    }
    
    Mat imageMatches,imageMatches02;
    drawMatches(image0,keypoints0,image1,keypoints1,matches01,
                imageMatches,Scalar(255,0,0));
    
    drawMatches(image0,keypoints0,image2,keypoints2,matches02,
                imageMatches02,Scalar(255,0,0));
    
    
    //////////////////
    //drawKeypoints(image1,keypoints1,img_keypoints_1,Scalar(255,0,0),DrawMatchesFlags::DRAW_RICH_KEYPOINTS);
    resize(imageMatches, imageMatches, Size(imageMatches.cols/2, imageMatches.rows/2));
    imshow("image1",imageMatches);
    
    resize(imageMatches02, imageMatches02, Size(imageMatches02.cols/2, imageMatches02.rows/2));
    imshow("image2",imageMatches02);
    //drawKeypoints( img_1, keypoints_1, img_keypoints_1, Scalar::all(-1), DrawMatchesFlags::DEFAULT );
    
   // imshow("image",img_keypoints_1);
    
    /*
    VideoCapture cap(0);
    
    while(true){
        Mat cam;
        cap.read(cam);
        imshow("Webcam",cam);
        cv::waitKey(1);
    }
     */
    waitKey(0);
    return 0;
}
