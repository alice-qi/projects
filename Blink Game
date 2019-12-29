# Name: Alice Qi
# Student Number: 20102432
# Date: July 8th 2019
# Assignment 1: Blink

"""
Description: This program allows 2 or 3 players to take turns in entering a letter which are added
together to form a word fragment. The objective of the game is to not be the player who forms a valid
English word while continuing to add letters to a word fragment of a potential valid English word.
"""


"""
This function reads the contents of a pure text file into a list.
The paramenter must be the name of the file in a string, in this case 'words.txt'. 
The function reads the text file line by line and adds each line to the list wordList.
The wordList list is returned once text file is completely read.
The textfile (words.txt) must be in the same folder as program to work, user will be prompted to drag text file into same program if it is not found.
""" 
def readFile(filename):
    try:
        inFile = open(filename, "r")
        # Initial empty list
        wordList = []
        for line in inFile:
            # .strip() function is used to remove '\n' from each line in the text file before adding line to wordList
            line = line.strip('\n')
            # .lower() function is used to read lines into lower case to case insensitize game
            wordList.append(line.lower())
        return wordList
    except:
        print("The file is not found. Please ensure the file is stored in the same folder as this program.")


"""
This function asks user whether they would like to play again, if yes (Y), function returns True, if no (N), function returns False.
The function outputs True if user inputs string 'Y' or 'y' and outputs false if user inputs string 'N' or 'n'.
"""
def playAgain():
    # While loop 
    while True:
        print()
        userInput = input("Would you like to play again? (Y/N): ").upper()
        if userInput in ('Y', 'N'):
            break
        else:
            print("Sorry, that was not an option")
    if userInput == 'Y':
        return True
    elif userInput == 'N':
        return False


"""
This function returns True if inputted word (string type) is in text file (words.txt), otherwise function returns False.
"""
def validWord(word):    
    wordList = readFile('words.txt')
    if word in wordList:
        return True
    else:
        return False


"""
Function returns True if word (string parameter) is the beginning of a word in text file (words.txt), otherwise returns False.
"""
def validBeginning(word):    
    with open('words.txt') as fh:
        wordlst = []
        for line in fh:
            # Removes '\n' from each line and changes all characters in string to lower case
            # Ex. 'Alice\n' becomes 'alice'
            line = line.strip('\n').lower()
            # Line is only appended to wordlst if it both starts with word and is longer than the word
            # Ex. If word is 'pea', 'pear' would be added to wordlst however 'pea' would not
            if line.startswith(word) and line > word:
                wordlst.append(line)
        if len(wordlst) != 0:
            return True
        else:
            return False


"""
Function returns True if player loses the game either by forming a word (string parameter) in words.txt file
that is longer than 3 letters or invalid beginning of a word found in words.txt file.
If the player does not lose, the function returns False.
"""
def gameOverCheck(word):
    if not validBeginning(word):
        print(word.upper(), "is not the beginning of a word.")
        return True
    if validWord(word) and len(word) > 3:
        print(word.upper(), "is a word.")
        return True
    else:
        return False
        
"""
Function validates the user entry to be one of the two options (a letter from A-Z or 2 to quit the game).
Function returns the user input for the first round of the game, either a letter from A-Z or 2 to quit, no option to challenge.
"""
def validFirstInput():
    while True:
        # User input is capitalized, making it case in-sensitive
        letter = input("Please enter one letter from A-Z or 2 to quit the game: ").upper()    
        # Checks if user input letter is just one letter in the English alphabet
        if letter in ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]:
            return letter
        elif letter == '2':
            return '2'
    

"""
Function validates the user entry to be one of the three options (a letter from A-Z, 1 for challenge, or 2 to quit the game).
Function returns a letter from A-Z, '1', or '2' depending on the user input and will loop until a valid entry is inputted.
"""
def validInput():
    while True:
        # User input is capitalized, making it case in-sensitive
        letter = input(("Please enter one letter from A-Z, 1 to challenge, or 2 to quit the game: ")).upper()    
        # Checks if user input letter is just one letter in the English alphabet
        if letter in ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"]:
            return letter
        elif letter == '1':
            return '1'
        elif letter == '2':
            return '2'


"""
Function will loop until user inputs a valid entry ('A' for 2 players or 'B' for 3 players).
"""
def validPlayerNumberInput():
    while True:
        playerNumber = input("Please enter A for 2 players or enter B for 3 players: ").upper()
        if playerNumber == "A":
            return "A"
        elif playerNumber == "B":
            return "B"
        else:
            print("Sorry, that was not an option")


"""
Function returns True if challenged player inputs a word beginning with wordFragmentList (list parameter)
and the word can be found in words.txt file, otherwise function returns False.
"""
def challenge(wordFragmentList):
    while True:
        wordInput = input().upper() 
        wordLowerCase = wordInput.lower()
        wordFragmentString = ("".join(str(x) for x in wordFragmentList)).lower()
        challengeWordFragment = list(wordInput)
        # Letters in user input word (wordInput) are deleted from list challengeWordFragment to determine if inputted word is longer than existing word fragment
        # Ex. If wordFragmentList = ['P', 'E', 'A'] and challengeWordFragment = ['P', 'E', 'A', 'R'], then challengeWordFragment[3] will be deleted to form ['P', 'E', 'A']
        del challengeWordFragment[len(wordFragmentList):len(challengeWordFragment)]     
        if  challengeWordFragment == wordFragmentList and len(wordInput)>len(wordFragmentString):
            if validWord(wordLowerCase):    
                print(wordInput, "is a word")
                return True
            else:
                print(wordInput, "is not a word")
                return False
        elif len(wordInput) == len(wordFragmentString):
            print("You must enter a word that is longer than current word fragment,", wordFragmentString.upper())
        else:
            print("You must enter a word that begins with current word fragment,", wordFragmentString.upper())   


"""
Function prints out user display including which Player's turn and the current word fragment.
Parameters of function include iterator used to alternate the player's turn and a list which is used to store the letters in current word fragment.
"""
def userDisplay(iterator, wordFragmentList):
    print()
    playerTurn = str(next(iterator))
    print("It is Player", playerTurn + "'s turn")
    # Prints each individual letter stored in a list as a string with no spaces
    print("Current word fragment:", "".join(str(x) for x in wordFragmentList))   


"""
Function runs through the game in two player mode. Two players take turns inputting letters and play until one player loses or a player quits the game.
Parameters of function include an iterator used to cycle through each Player's turn and a list of the letters in current word fragment.
Function prints the winner of the game and exits function when the one player loses.
"""
def twoPlayerGame(myIterator, wordFragment):
    userDisplay(myIterator, wordFragment)    
    firstInput = validFirstInput()
    if firstInput == '2':
        print()
        print("Thank you for playing Blink!")
        # If user enters '2' to quit, system exit to quit program
        raise SystemExit
    else:
        wordFragment.append(firstInput)

    game = 'playing'
    while game != 'done':
        userDisplay(myIterator, wordFragment)
        userInput = validInput()
        if userInput == '2':
            print()
            print("Thank you for playing Blink!")
            raise SystemExit
        elif userInput == '1':
            print()
            playerTurn = str(next(myIterator))
            print("Player", playerTurn, "please enter a word that begins with", "".join(str(x) for x in wordFragment))
            challengeResult = challenge(wordFragment)
            if challengeResult:
                print("GAME OVER! Player", playerTurn, "wins!") 
                game = 'done'
            else:
                playerTurn = str(next(myIterator))
                print("GAME OVER! Player", playerTurn, "wins!")
                game = 'done'   
        else:
            wordFragment.append(userInput)
            wordFragmentString = ("".join(str(x) for x in wordFragment)).lower()
            if gameOverCheck(wordFragmentString):
                playerTurn = str(next(myIterator))
                print("GAME OVER! Player", playerTurn, "wins!")
                game = 'done'
            else:
                game = 'playing'


"""
Function runs through game in two player mode once one player is eliminated from three player mode.
Two players take turns inputting letters and play until one player loses or a player quits the game.
Parameters of function include an iterator used to cycle through each Player's turn and a list of the letters in current word fragment.
Function prints which player wins and exits function when one player loses.
"""
def thirdPlayerEliminated(myIterator, wordFragment):
    game = 'playing'
    while game != 'done':
        userDisplay(myIterator, wordFragment)
        userInput = validInput()
        if userInput == '2':
            print()
            print("Thank you for playing Blink!")
            raise SystemExit
        elif userInput == '1':
            print()
            playerTurn = str(next(myIterator))
            print("Player", playerTurn, "please enter a word that begins with", "".join(str(x) for x in wordFragment))
            challengeResult = challenge(wordFragment)
            if challengeResult:
                print("GAME OVER! Player", playerTurn, "wins!") 
                game = 'done'
            else:
                playerTurn = str(next(myIterator))
                print("GAME OVER! Player", playerTurn, "wins!")
                game = 'done'   
        else:
            wordFragment.append(userInput)
            wordFragmentString = ("".join(str(x) for x in wordFragment)).lower()
            if gameOverCheck(wordFragmentString):
                playerTurn = str(next(myIterator))
                print("GAME OVER! Player", playerTurn, "wins!")
                game = 'done'
            else:
                game = 'playing'
                

"""
Function runs through game in three player mode. Three players take turns inputting letters and play until one player loses
or a player quits the game. If no player quits, then game continues into two player mode. 
Parameters of function include an iterator used to cycle through each Player's turn and a list of the letters in current word fragment.
"""
def threePlayerGame(myIterator, wordFragment):
    userDisplay(myIterator, wordFragment)
    firstInput = validFirstInput()
    if firstInput == '2':
        print()
        print("Thank you for playing Blink!")
        raise SystemExit
    else:
        wordFragment.append(firstInput)
    
    game = 'playing'
    while game != 'done':
        userDisplay(myIterator, wordFragment)
        userInput = validInput()
        if userInput == '2':
            print()
            print("Thank you for playing Blink!")
            raise SystemExit
        elif userInput == '1':
            print()
            playerTurn = str(next(myIterator))
            playerTurn = str(next(myIterator))
            print("Player", playerTurn, "please enter a word that begins with", "".join(str(x) for x in wordFragment))
            challengeResult = challenge(wordFragment)
            if challengeResult:
                playerTurn = str(next(myIterator))
                print("Player", playerTurn, "is eliminated!")
                myIterator = myIteratorAdjustment(playerTurn)
                if playerTurn == '2':
                    playerTurn = str(next(myIterator))
                thirdPlayerEliminated(myIterator, wordFragment)
                game = 'done'
            else:
                print("Player", playerTurn, "is eliminated!")
                myIterator = myIteratorAdjustment(playerTurn)
                if playerTurn == '2':
                    playerTurn = str(next(myIterator))
                thirdPlayerEliminated(myIterator, wordFragment)
                game = 'done'
        else:
            wordFragment.append(userInput)
            wordFragmentString = ("".join(str(x) for x in wordFragment)).lower()
            if gameOverCheck(wordFragmentString):
                playerTurn = str(next(myIterator))
                playerTurn = str(next(myIterator))
                playerTurn = str(next(myIterator))
                print("Player", playerTurn, "is eliminated!")
                myIterator = myIteratorAdjustment(playerTurn)
                if playerTurn == '2':
                    playerTurn = str(next(myIterator))
                thirdPlayerEliminated(myIterator, wordFragment)
                game = 'done'
            else:
                game = 'playing'
                

"""
Function returns the redefined myIterator range based on which of the three players was eliminated.
The parameter for the function is the string number of the eliminated Player.
"""
def myIteratorAdjustment(playerTurn):
    if playerTurn == '1':
        # myIterator will cycle between elements in list [2, 3]
        return cycle(range(2,4))
    elif playerTurn == '2':
        # myIterator will cycle between elements in list [1, 3]
        return cycle(range(1,4,2))
    else:
        # myIterator will cycle between elements in list [1, 2]
        return cycle(range(1,3))


from itertools import cycle
"""
Main function to execute the Blink game.
Game will continue to loop until user chooses not to play again or quits.
Assumption 1: In my program I assume that when one player is eliminated from 3 player mode, it goes to the next player's turn by number.
              For example, if Player 1 is eliminated -> Player 2's turn next, if Player 2 is eliminated -> Player 3's turn next, and
              if Player 3 is eliminated -> Player 1's turn next.
Assumption 2: If the player inputs a valid 2 or 3 letter word that is NOT the beginning of any other word, that player
              would lose for creating a word fragment that could never result in another word.
              For example, 'PLF' is a valid 3 letter word but is not the beginning of any other word, the player that inputs
              the final letter 'F' would lose for entering a word fragment that could not result in any other word.
"""
def main():
    while True:

        print()
        print("Welcome to Blink! Good luck...")
        print()    

        wordFragment = []
        
        playersNumber = validPlayerNumberInput()
        if playersNumber == 'A':
            myIterator = cycle(range(1,3))
            twoPlayerGame(myIterator, wordFragment)
            if playAgain():
                continue
            else:
                print()
                print("Thank you for playing Blink!")
                break
            
        elif playersNumber == 'B':
            myIterator = cycle(range(1,4))
            threePlayerGame(myIterator, wordFragment)
            if playAgain():
                continue
            else:
                print()
                print("Thank you for playing Blink!")
                break 

main()

