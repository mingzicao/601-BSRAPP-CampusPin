import tensorflow as tf
import sys
import os
# change this as you see fit
filedirectory = sys.argv[1]
image_path = os.listdir(filedirectory)
accuracy = 0
wrong = 0
for i in range(1,20):#len(image_path)
    # read in the image_data
    image_data = tf.gfile.FastGFile(filedirectory+'/'+image_path[i], 'rb').read()

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

        predictions = sess.run(softmax_tensor, \
            {'DecodeJpeg/contents:0': image_data})

        # Sort to show labels of first prediction in order of confidence
        top_k = predictions[0].argsort()[-len(predictions[0]):][::-1]
        score_max = 0
        for node_id in top_k:
            human_string = label_lines[node_id]
            #print(node_id)
            score = predictions[0][node_id]
            if score>score_max:
                score_max = score
                max_id = node_id
            #print(score)
            
            #print('%s (score = %.5f)' % (human_string, score))
        print(label_lines[max_id])
        if label_lines[max_id]=="class1 chapel":
            accuracy = accuracy + 1
        else:
            wrong = wrong + 1
    print 'right:',accuracy
    print 'wrong:',wrong
    print('.')
percent = 0.0
percent = (accuracy*100)/20
print 'accuracy is',percent,'%'
