#coding:utf-8
__author__='XJX'
__date__='2018.02.06'

"""
description:
    添加自定义词典的词频（定义为50）和词性（定义为名词）
"""

import os
import codecs
import sys
import importlib 
importlib.reload(sys)

in_txt = r"/Users/xujiaxing/Documents/GitHub/Smart-contract/data/sougou.txt"
out_txt = r"/Users/xujiaxing/Documents/GitHub/Smart-contract/data/userdict.txt"

f1 = open(in_txt,'r',encoding='utf-8')
f2 = open(out_txt,'a',encoding='utf-8')

line = f1.readline()
line = line.strip('\n')#去除行末尾换行
#print('GG')
while line:
    try:
        print(line)
        output = line+' '+'50'+' '+'n'+'\n'
        f2.write(output)
        print(output)
        line = f1.readline()
        line = line.strip('\n')#去除行末尾换行
    except  Exception:
        print('error')
        print(line)
        line = f1.readline()
        line = line.strip('\n')#去除行末尾换行
        continue

f1.close()
f2.close()
