
# script developed for fetching all comments on the FB post https://www.facebook.com/groups/313194722097809/permalink/869462376471038/
# quora answer for the same generated using this script --->  https://www.quora.com/Who-is-Mr-Narayan-Suresh-Manjarekar/answer/Anubhav-Chaturvedi-1


import json 
import httplib, urllib
import pickle

API_KEY = "GRAPH_API_KEY"
COMMENT_LIKE_THRESHOLD = 10

POST_thread_comments_params = urllib.urlencode({  "access_token" : API_KEY,
                                   "format" : "json",
                                   "method" : "get",
                                   "limit" : 5000,
                                   "debug" : "all",
                                   "pretty" : 0,
                                    "suppress_http_code" : 0 })
POST_URL = "graph.facebook.com"

# 869462376471038 is the id of the post 
POST_URL_PATH = "/v2.3/869462376471038/comments"
headers = {"Content-type": "application/x-www-form-urlencoded",
           "Accept": "text/plain",  "scheme" : "https"}

conn = httplib.HTTPSConnection(POST_URL)
conn.request("POST", POST_URL_PATH,
             POST_thread_comments_params, headers)
response = conn.getresponse()
#print response.status, response.reason
data = response.read()
conn.close()
data = json.loads(data)


comments = data["data"]


# fetch any images/memes URL that have been posted as comment
def getImageURL(comment_id):
    POST_thread_comments_params = urllib.urlencode({  "access_token" : API_KEY,
                                   "format" : "json",
                                   "method" : "get",
                                   "fields" : "attachment,id",
                                   "pretty" : 0,
                                    "suppress_http_code" : 0 })
    POST_URL = "graph.facebook.com"
    POST_URL_PATH = "/v2.3/%d"%comment_id
    headers = {"Content-type": "application/x-www-form-urlencoded",
           "Accept": "text/plain",  "scheme" : "https"}


    conn = httplib.HTTPSConnection(POST_URL)
    conn.request("POST", POST_URL_PATH, POST_thread_comments_params, headers)
    response = conn.getresponse()
    print response.status, response.reason
    data = response.read()
    conn.close()
    data = json.loads(data)
    if "attachment" in data.keys():
        return data["attachment"]["media"]["image"]["src"]
    else:
        return None


# store the image url results because it makes updating the list faster...
# why check if the given comment has any image or not it you have already done it once
image_url_cache = {}
with open('image_url_cache.pickle', 'rb') as handle:
    image_url_cache = pickle.load(handle)


for comment in comments:
    comment_id = long(comment["id"])
    comment_from = comment["from"]["name"]
    comment_likes = comment["like_count"]
    comment_message = comment["message"]
    if comment_likes > COMMENT_LIKE_THRESHOLD:
        if comment_id not in image_url_cache.keys():
            image_url_cache[comment_id] = getImageURL(comment_id)
        comment_image = image_url_cache[comment_id]
        print "<<<<<< %s >>>>>>" % comment_from
        print comment_message
        if comment_image is not None:
            print comment_image
        print
        print
        
# save the image url cache
with open('image_url_cache.pickle', 'wb') as handle:
    pickle.dump(image_url_cache, handle)

