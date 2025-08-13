# Import pandas
import pandas
#writing data to csv (Activity 17)
# Create a Dictionary that will hold our data
data = {
  "Usernames": ["admin", "Charles", "Deku"],
  "Passwords": ["password", "Charl13", "AllMight"]
}

# Create a DataFrame using that data
dataframe = pandas.DataFrame(data)

# Print the DataFrame
print(dataframe)


dataframe.to_csv("./sample.csv", index=False)

#reading data from csv (Activity 18)

 
# Read the CSV file and store it into a DataFrame
dataframe = pandas.read_csv("./sample.csv")
 
# Print the full data
print("Full Data: ")
print(dataframe)
 
# Print the values in the Usernames column only
print("===============")
print("Usernames:")
print(dataframe["Usernames"])
 
# Print the username and password of the second row
print("===============")
print("Username: ", dataframe["Usernames"][1], " | ", "Password: ", dataframe["Passwords"][1])
 
#Sort the Usernames column in ascending order
print("====================================")
print("Data sorted in ascending Usernames:")
print(dataframe.sort_values('Usernames'))
 
#Sort the Passwords column in descending order
print("====================================")
print("Data sorted in descending Passwords:")
print(dataframe.sort_values('Passwords', ascending=False))