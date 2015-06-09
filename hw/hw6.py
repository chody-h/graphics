
import numpy


matrix = []

matrix.append([10, 11, 9, 25, 22])
matrix.append([8, 10, 9, 26, 28])
matrix.append([9, 99, 9, 24, 25])
matrix.append([11, 11, 12, 23, 22])
matrix.append([10, 11, 9, 22, 25])





######### MEAN ##########
# calc_matrix = []
# for i in range(0, 5):
# 	calc_matrix.append([])

# for row in range(0, 5):
# 	for col in range(0, 5):

# 		sum = 0.0
# 		count = 0.0

# 		for row_near in range(-1, 2):
# 			for col_near in range(-1, 2):
# 				row_idx = row + row_near
# 				col_idx = col + col_near
# 				if row_idx > 4 or row_idx < 0 or col_idx > 4 or col_idx < 0:
# 					continue
# 				sum += matrix[row_idx][col_idx]
# 				count += 1

# 		calc_matrix[row].append((int)(sum/count + 0.5))

# for row in calc_matrix:
# 	print "%d %d %d %d %d" % (row[0], row[1], row[2], row[3], row[4])




######### MEDIAN ##########
# calc_matrix = []
# for i in range(0, 5):
# 	calc_matrix.append([])

# for row in range(0, 5):
# 	for col in range(0, 5):

# 		neighbors = []

# 		for row_near in range(-1, 2):
# 			for col_near in range(-1, 2):
# 				row_idx = row + row_near
# 				col_idx = col + col_near
# 				if row_idx > 4 or row_idx < 0 or col_idx > 4 or col_idx < 0:
# 					continue
# 				neighbors.append(matrix[row_idx][col_idx])

# 		mean = numpy.median(neighbors)
# 		print row, col, sorted(neighbors), mean
# 		calc_matrix[row].append((int)(mean + 0.5))

# for row in calc_matrix:
# 	print "%d %d %d %d %d" % (row[0], row[1], row[2], row[3], row[4])





######### UNSHARP ##########
# A = 1
# calc_matrix = []
# for i in range(0, 5):
# 	calc_matrix.append([])

# for row in range(0, 5):
# 	for col in range(0, 5):

# 		value = matrix[row][col] * (A + 4) * 1.0/A
# 		# print value
# 		if col-1 >= 0:
# 			value += (matrix[row][col-1] * -1) * 1.0/A
# 		# print value
# 		if col+1 < 5:
# 			value += (matrix[row][col+1] * -1) * 1.0/A
# 		# print value
# 		if row-1 >= 0:
# 			value += (matrix[row-1][col] * -1) * 1.0/A
# 		# print value
# 		if row+1 < 5:
# 			value += (matrix[row+1][col] * -1) * 1.0/A
# 		# print value

# 		# if value < 0:
# 		# 	value = 0
# 		calc_matrix[row].append(value)
# 		# quit()

# for row in calc_matrix:
# 	print "%d %d %d %d %d" % (row[0], row[1], row[2], row[3], row[4])






######### EDGE ##########
A = 1
calc_x_matrix = []
calc_y_matrix = []
calc_matrix = []
for i in range(0, 3):
	calc_x_matrix.append([])
	calc_y_matrix.append([])
	calc_matrix.append([])

for row in range(1, 4):
	for col in range(1, 4):

		value_x = 0
		value_y = 0


		val = matrix[row-1][col-1]
		value_x += val*-1
		value_y += val*-1

		val = matrix[row][col-1]
		value_x += val*-2
		value_y += val*0

		val = matrix[row+1][col-1]
		value_x += val*-1
		value_y += val*1


		val = matrix[row-1][col]
		value_x += val*0
		value_y += val*-2

		val = matrix[row][col]
		value_x += val*0
		value_y += val*0

		val = matrix[row+1][col]
		value_x = val*0
		value_y = val*2


		val = matrix[row-1][col+1]
		value_x += val*1
		value_y += val*-1

		val = matrix[row][col+1]
		value_x += val*2
		value_y += val*0

		val = matrix[row+1][col+1]
		value_x += val*1
		value_y += val*1

		# if value < 0:
		# 	value = 0
		value_x /= 8.0
		value_y /= 8.0
		value = value_x + value_y
		calc_x_matrix[row-1].append(value_x)
		calc_y_matrix[row-1].append(value_y)
		calc_matrix[row-1].append(value)
		# quit()

for row in calc_x_matrix:
	print "%d %d %d" % (row[0], row[1], row[2])
print ""
for row in calc_y_matrix:
	print "%d %d %d" % (row[0], row[1], row[2])
print ""
for row in calc_matrix:
	print "%d %d %d" % (row[0], row[1], row[2])
