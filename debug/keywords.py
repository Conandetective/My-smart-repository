#coding:utf-8
__author__='LW'
__date__='2018.02.23'

"""
description:
    提取字段类型
"""

import jieba
import jieba.analyse
import re
import os
import codecs
import sys
import importlib 

def Keywords():
    in_text = r"../data/data.txt"  # 原合同文本
    map_text = r"../data/keyword.txt"  # 储存字段映射
    ans_text = r"../data/ans.txt"  # 储存最终结果

    f1 = open(in_text,'r',encoding='utf8')
    f2 = open(map_text,'r',encoding='utf8')
    f3 = open(ans_text, 'w', encoding='utf8')

    jieba.load_userdict("../data/userdict.txt")
    stoplist = {}.fromkeys([ line.strip() for line in open("../data/stopwords.txt") ])
    wordlist = {}.fromkeys([ line.strip() for line in open("../data/words1.txt") ])
    wordslist = {}.fromkeys([line.strip() for line in open("../data/words.txt")])

    wordslist[u'押金百分比'] = 100
    wordslist[u'租金付费方式'] = u'月付'
    yi = 0
    xuzu = 0
    num = 1
    s = []
    line = f1.readline()
    while line:
        try:
            tag = '##' + str(num) +'##'
            #print(tag)
            flag = re.findall(tag, line)#匹配标记所在行
            if flag:
                ans = f2.readline()[:-1]#获取字段映射
                #print(line)

                keys = jieba.cut(line, cut_all=False)
                keys = [word for word in list(keys) if word not in stoplist]#去停用词
                keys = [word for word in list(keys) if word in wordlist]
                #print(keys)

                for key in keys:
                    #print(ans)
                    if yi == 0 and key == u'姓名':
                        wordslist[u'甲方姓名'] = ans
                    elif yi == 0 and key == u'电话号码' or yi == 0 and key == u'电话':
                        wordslist[u'甲方电话号码'] = ans
                    elif yi == 0 and key == u'身份证号码':
                        wordslist[u'甲方身份证号码'] = ans
                        yi = 1
                    elif yi == 0 and key == u'住址' or yi == 0 and key == u'地址':
                        wordslist[u'甲方住址'] = ans
                    elif yi == 1 and key == u'姓名':
                        wordslist[u'乙方姓名'] = ans
                    elif yi == 1 and key == u'电话号码' or yi == 1 and key == u'电话':
                        wordslist[u'乙方电话号码'] = ans
                    elif yi == 1 and key == u'身份证号码':
                        wordslist[u'乙方身份证号码'] = ans
                    elif yi == 1 and key == u'住址' or yi == 1 and key == u'地址':
                        wordslist[u'乙方住址'] = ans
                    elif key == u'名称' or key == u'品名':
                        wordslist[u'租用物品名称'] = ans
                    elif key == u'件数':
                        wordslist[u'租用物品件数'] = ans
                    elif key == u'单价':
                        wordslist[u'租用物品单价'] = ans
                    elif key == u'备注':
                        wordslist[u'租用物品备注'] = ans
                    elif key == u'月付' or key == u'日付' or key == u'合同起始日期一次性付清':
                        wordslist[u'租金付费方式'] = ans
                    elif key == u'现金方式':
                        wordslist[u'租金费用'] = ans
                    elif key == u'缴纳':
                        wordslist[u'押金数目'] = ans
                    elif key == u'视为100':
                        wordslist[u'押金百分比'] = ans
                    elif key == u'交货':
                        wordslist[u'交货日期'] = ans
                    elif key == u'返还':
                        wordslist[u'归还日期'] = ans
                    elif key == u'续租':
                        wordslist[u'续租约定'] = ans
                        if ans == u'允许':
                            xuzu = 1
                    elif key == u'租金':
                        if xuzu == 1:
                            wordslist[u'续租百分比'] = ans
                        else:
                            wordslist[u'续租百分比'] = 0
                    elif key == u'担保单位':
                        wordslist[u'担保单位'] = ans
                    elif key == u'合同开始日期':
                        wordslist[u'合同开始日期'] = ans
                    elif key == u'合同结束日期':
                        wordslist[u'合同结束日期'] = ans
                    elif key == u'签订日期':
                        wordslist[u'签订日期'] = ans

                num += 1
            line = f1.readline()

        except Exception:
            print('error')
            print(line)
            line = f1.readline()
            continue

    print(wordslist)

    list_word = [u'甲方姓名', u'甲方电话号码', u'甲方身份证号码', u'甲方住址', u'乙方姓名',
                 u'乙方电话号码', u'乙方身份证号码', u'乙方住址', u'租用物品名称', u'租用物品单价',
                 u'租用物品件数', u'租用物品备注', u'租金付费方式', u'租金费用', u'押金数目',
                 u'押金百分比', u'续租约定', u'续租百分比', u'交货日期', u'归还日期',
                 u'合同开始日期', u'合同结束日期', u'担保单位', u'签订日期', ]

    for line4 in list_word:
        #print(line4)
        for key, value in wordslist.items():
           if key == line4:
                f3.write(str(key) + '：' + str(value) + '\n')
                break

    f1.close()
    f2.close()
    f3.close()

if __name__ == '__main__':
    Keywords()
