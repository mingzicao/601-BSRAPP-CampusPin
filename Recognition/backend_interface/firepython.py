from firebase import firebase
import urllib
import tensorflow as tf
import sys
import time
import os

image_path = "test.jpg"#sys.argv[1]
firebase1 = firebase.FirebaseApplication('https://campuspin-5d264.firebaseio.com/', None)

def readfire():
    result = firebase1.get('/Building/PHO/', None)
    [username] = firebase1.get('/user', None).keys()
    url = result.values()
    #print(url)
    ulen = len(url)
    #print(ulen)
    #url = result[u'-KyqbHZV0E1NLAwHMJbQ'][u'photoUrl']
    urllib.urlretrieve(url[0], "test1.jpg")
    return username,ulen

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
      result = "uknown class\n\n"+list1[0]+"\n"+list1[1]
    else:
      result = list1[0]
    return result

def postfire(result,username):
    index = 4
    firebase1.put('user','/'+username+'/searchHistory'+'/'+str(index),result)
    
def main():
    #username,ulen = readfire()
    post = recognition()
    postfire(post,'a')
    os.remove('test.jpg')
    # oldulen = 0
    # for _ in range(10):
    #     username,ulen = readfire()
    #     print(ulen,oldulen)
    #     if(ulen != oldulen):
    #         post = recognition()
    #         postfire(post,username)
    #         oldulen = ulen
    #     os.remove('test.jpg')
    #     time.sleep(5)


if __name__ == '__main__':
	 main()
