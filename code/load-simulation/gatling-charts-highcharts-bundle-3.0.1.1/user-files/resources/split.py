# -*- coding: utf-8 -*-
# 
import os
import sys
import csv
 
# 初始化编码
reload(sys)
sys.setdefaultencoding('utf-8')
 
# 读取本地csv文件
csv_path = os.path.join(os.path.dirname(os.path.abspath(__file__)),'csv','ACT_RE_PROCDEF.csv')
csv_reader = csv.reader(open(csv_path,'rb'))
csv_reader.next()
i=j=1
for row in csv_reader:
	if i%11==0:
		print u"CSV文件source%s已生成成功" % j
		j+=1
	# 写入csv
	csv_path = os.path.join(os.path.dirname(os.path.abspath(__file__)),'csv','source'+str(j)+'.csv')
	csv_file = file(csv_path, 'ab+')
	csv_write = csv.writer(csv_file)
	# 文件不存在则写入头部	
	if os.path.getsize(csv_path)==0:
		csv_write.writerow(['processDefinitionId'])
	# 写入数据
	csv_write.writerow([row[0])
	csv_file.close()
	i+=1
# 关闭连接