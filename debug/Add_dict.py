__author__='XJX'
__date__='2018.02.10'
# -*- coding: utf-8 -*-

"""
description:
    Add '##number##' in userdict.txt
"""

import os
import re
import codecs
import sys

txt = r"../data/userdict.txt"

f = open(txt,'a',encoding='utf8')

for i in range(1,10000):
    f.write('##'+str(i)+'## 1 n\n')