__author__='XJX'
__date__='2018.02.08'
# -*- coding: utf-8 -*-

"""
description:
    将一个目录下所有doc和docx文件转成txt
    该目录下创建一个新目录newdir
    新目录下fileNames.txt创建一个文本存入所有的word文件名
    本版本具有一定的容错性，即允许对同一文件夹多次操作而不发生冲突
"""

import docx2txt
import os
import re
import codecs
import sys
import importlib
import fnmatch



all_FileNum = 0
debug = 0


def Translate(path):
    global debug, all_FileNum
    if debug:
        print(path)
    #该目录下所有文件的名字
    files = os.listdir(path)
    #该目下创建一个新目录newdir，用来放转化后的txt文本
    New_dir = os.path.abspath(os.path.join(path, 'newdir'))
    if not os.path.exists(New_dir):
        os.mkdir(New_dir)
    if debug:
        print(New_dir)
    #创建一个文本存入所有的word文件名
    fileNameSet= os.path.abspath(os.path.join(New_dir, 'fileNames.txt'))
    o = open(fileNameSet,"w")
    try:
        for filename in files:
            print(filename)
            if debug:
                print(filename)
            #如果不是word文件：继续
            if not fnmatch.fnmatch(filename, '*.doc') and not fnmatch.fnmatch(filename, '*.docx'):
                continue;
            #如果是word临时文件：继续
            if fnmatch.fnmatch(filename, '~$*'):
                continue;
            if debug:
                print(filename)
            docpath = os.path.abspath(os.path.join(path,filename))

            #得到一个新的文件名,把原文件名的后缀改成txt
            new_txt_name = ''
            if fnmatch.fnmatch(filename, '*.doc'):
                new_txt_name = filename[:-4]+'.txt'
            else:
                new_txt_name = filename[:-5]+'.txt'
            if debug:
                print(new_txt_name)
            word_to_txt = os.path.join(os.path.join(path, 'newdir'),new_txt_name)
            print(word_to_txt)
            text = docx2txt.process(docpath)
            new_file = open('../data/datadir/newdir/' + new_txt_name,'w')
            new_file.write(text)
            new_file.close()
            o.write(word_to_txt+'\n')
            all_FileNum += 1
    #finally:
        #wordapp.Quit()
    except:
        print('error')

    o.close()
    o = open(fileNameSet, "r",encoding='utf8')
    aimpath = o.readline()[:-1]
    print('aim is: ' + aimpath)
    o.close()

    return aimpath

if __name__ == '__main__':
    # print('''
    #     将一个目录下所有doc和docx文件转成txt
    #     该目下创建一个新目录newdir
    #     新目录下fileNames.txt创建一个文本存入所有的word文件名
    #     本程序具有一定的容错性
    # ''')
    # print('Enter your Director\'s path:')
    # print("路径用\或\\表示均可")
    # mypath = input()
    mypath = '../data/datadir'
    print ('生成的文件有:')
    Translate(mypath)
    print('The Total Files Numbers = ', all_FileNum)