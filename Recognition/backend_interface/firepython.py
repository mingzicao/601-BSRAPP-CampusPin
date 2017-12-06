from firebase import firebase
import urllib
import tensorflow as tf
import sys
import time
import os

image_path = "test.jpg"#sys.argv[1]
firebase1 = firebase.FirebaseApplication('https://campuspin-5d264.firebaseio.com/', None)

def readfire():
    result = firebase1.get('/user', None)
    username = firebase1.get('/currentUser', None)
    url = result[username][u'picturesSearchHistory ']
    ulen = len(url)
    #print(ulen)
    #url = result[u'-KyqbHZV0E1NLAwHMJbQ'][u'photoUrl']
    #urllib.urlretrieve(url[ulen-1], "test.jpg")
    return username,ulen,url

def recognition():
   # read in the image_data
    image_data = tf.gfile.FastGFile(image_path, 'rb').read()

    # Loads label file, strips off carriage return
    label_lines = [line.rstrip() for line in tf.gfile.GFile("retrained_labels.txt")]

    # Unpersists graph from file
    with tf.gfile.FastGFile("retrained_graph.pb", 'rb') as f:
      graph_def = tf.GraphDef()
      graph_def.ParseFromString(f.read())
      _ = tf.import_graph_def(graph_def, name='')

    with tf.Session() as sess:
      # Feed the image_data as input to the graph and get first prediction
      softmax_tensor = sess.graph.get_tensor_by_name('final_result:0')

      predictions = sess.run(softmax_tensor,{'DecodeJpeg/contents:0': image_data})

      # Sort to show labels of first prediction in order of confidence
      top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]

      score = {}
      list1 = []
    for node_id in top_k:
      human_string = label_lines[node_id]
      score[human_string] = (predictions[0][node_id])
    list1 = sorted(score, key = score.__getitem__, reverse=True)


    #print('%s (score = %.5f)' % (human_string, score))
    maxscore = max(score.values())
    if maxscore < 0.55:
      result = "uknown class, most likely: (1) "+list1[0]+"; (2) "+list1[1]
    else:
      result = list1[0]

    
    if result == 'class1 chapel':
        output = 'Marsh Chapel'
    elif result == 'class2 pho':
        output = 'PHO'
    elif result == 'class3 aa':
        output = 'Agganis Arena'
    elif result == 'class4 com':
        output = 'Communication Research Center'
    else:
        output = result 
    return output

def postfire(result,username):
    #index = 'recognition_result'
    firebase1.put('user','/'+username+'/result',result)#'/searchHistory'+'/'+str(index)
    
def main():
    # username,ulen,urllist = readfire()
    # post = recognition()
    # postfire(post,'a')
    # os.remove('test.jpg')
    urldic = {}
    oldurldic = {}
    updateurl = {}

    #initial
    username,ulen,urldic = readfire()
    oldurldic = urldic
    oldulen = ulen
    #end initial

    for _ in range(100):#change the number here, 10 means 1 minutes.
        username,ulen,urldic = readfire()
        if(ulen != oldulen):
            updateurl = dict(set(urldic.items())-set(oldurldic.items()))
            #update checker
            oldurldic = urldic
            oldulen = ulen
            if(updateurl == {}):
                1
            else:
                [link] = updateurl.values()
                urllib.urlretrieve(link, "test.jpg")
                post = recognition()
                postfire(post,username)
                os.remove('test.jpg')
            # print(updateurl)
        #     username,ulen = readfire()
        #     post = recognition()
        #     postfire(post,username)
        #     oldulen = ulen
        # os.remove('test.jpg')
        time.sleep(5)


if __name__ == '__main__':
	 main()
