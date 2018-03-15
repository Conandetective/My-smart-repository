__author__='XJX'
__date__='2018.03.08'
# -*- coding: utf-8 -*-

"""
description:
    将path路径指定docx文件转成txt
"""
import docx2txt
import os
import re
import codecs
import sys
import importlib
import fnmatch


debug = 0


def Translate(path):
    global debug
    if debug:
        print(path)

    new_txt_name = ''

    try:
        #得到一个新的文件名,把原文件名的后缀改成txt
        new_txt_name = path[:-5]+'.txt'
        if debug:
            print(new_txt_name)
        word_to_txt = os.path.join(os.path.join(path, 'newdir'),new_txt_name)
        print(word_to_txt)
        text = docx2txt.process(path)
        # txtpath = os.path.abspath(os.path.join(path, os.pardir))
        new_file = open(new_txt_name,'w')
        new_file.write(text)
        new_file.close()
    #finally:
        #wordapp.Quit()
    except:
        print('error')


    print('aim is: ' + new_txt_name)

    return new_txt_name

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
    mypath = '../data/datadir/租赁合同.docx'

    Translate(mypath)