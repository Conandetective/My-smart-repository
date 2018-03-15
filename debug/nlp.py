__author__='XJX'
__date__='2018.02.08'
# -*- coding: utf-8 -*-

from doc2txt_only import *
from regular_expression import *
from keywords import *
import logging

def nlp():
    mypath = r'../data/datadir/租赁合同.docx'
    aimpath = Translate(mypath)
    Extract(aimpath)
    Keywords()

if __name__=='__main__':
    nlp()
