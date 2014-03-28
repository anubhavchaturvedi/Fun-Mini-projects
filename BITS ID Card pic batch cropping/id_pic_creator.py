from cv2_detect import detect
import cv2
import cv2.cv as cv
import os

def detect(img, cascade_fn='haarcascades/haarcascade_frontalface_alt.xml',scaleFactor=1.3, minNeighbors=3, minSize=(20,20),flags=cv.CV_HAAR_SCALE_IMAGE):
    cascade = cv2.CascadeClassifier(cascade_fn)
    rects = cascade.detectMultiScale(img, scaleFactor=scaleFactor,minNeighbors=minNeighbors,minSize=minSize, flags=flags)
    if len(rects) == 0:
        return []
    rects[:, 2:] += rects[:, :2]
    return rects

def draw_rects(img, rects, color):
    for x1, y1, x2, y2 in rects:
        cv2.rectangle(img, (x1-50, y1-80), (x2+50, y2+80), color, 2)


def demo(in_fn, out_fn):
    img_color = cv2.imread(in_fn)
    img_gray = cv2.cvtColor(img_color, cv.CV_RGB2GRAY)
    img_gray = cv2.equalizeHist(img_gray)
    #print in_fn, img_gray.shape

    #print ">>> Detecting faces..."
    rects = detect(img_gray)
    img_out = img_color.copy()
    draw_rects(img_out, rects, (0, 255, 0))
    for x1,y1,x2,y2 in rects:
        img_out = img_out[(y1-80):(y2+80),(x1-50):(x2+50)]
    cv2.imwrite(out_fn, img_out)


def main():
    files=os.listdir("/home/anubhav/FaceDetection/pics/raw")
    for file in files:
        demo('/home/anubhav/FaceDetection/pics/raw/'+file, '/home/anubhav/FaceDetection/pics/edit/'+file)


if __name__ == '__main__':
    main()
