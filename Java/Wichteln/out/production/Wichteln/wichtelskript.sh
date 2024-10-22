#!/bin/bash

# File to read
input_file="Ziehung"

# Iterate over each line in the file
while IFS= read -r line; do
    # Extract the first word of the line
    first_word=$(echo "$line" | awk '{print $1}')
    
    # Append the line to a file named after the first word
    echo "$line" >> "$first_word"
done < "$input_file"

