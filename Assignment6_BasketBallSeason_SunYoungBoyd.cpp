/*==========================================================

 Title:     Assignment 6 - C++ Basketball Season
 Course:  	CIS 2252
 Author:  	<Sun Young Boyd>
 Date:    	<10/05/23>
 Description: C++ Basketball Season

 ==========================================================
*/

#include <iostream>

using namespace std;

void alterGameScore(int pArr[], int gameNumber) {
    // Declare a variable k and prompt the user to 
    // enter how many points they wish to add to one of the five games they had selected
    int k;
    cin >> k;
    pArr[gameNumber] += k;
    cout << endl;
}

void printScores(int pArr[], int gameMAX) {
	// loop to print all game scores
	for (int i = 0; i < gameMAX; i++) {
	    if (i > 0) {
            cout << " ";
	    } cout << pArr[i];
	} cout << endl;
}

int totalScore(int pArr[], int gameMAX) {
    // Return the sum of all game scores
    int total = 0;
    for (int i = 0; i < gameMAX; i++) {
        total += pArr[i];
    }
    return total;
    
}

int main() {

    // !!! No need to modify anything here in the main() !!!

	const int gameMAX = 5; // There are 5 games in the season
	int* pArr;
	pArr = new int[gameMAX];

    // With every upcoming game up to game #5, your team got better in scoring the points, 
    // e.g. game 1 = 21 pts, game 2 = 32 pts, game3 = 43, game4 = 54, game 5 = 65 pts.
    // This is determined by a formula given below. (You dont have to worry too much about the formula itself) 
	for (int i = 0; i < gameMAX; i++) {
    	*(pArr + i) = ((i + 2) * 10) + (i + 1); // Formula that determines points for each game
        
        /* Formula EXPLAINED
        * Iteration1: i=0 starting position 
		* *(pArr + i)               : first array element (index 0). The alternative way to write this is pArr[i], and in the 1st iteration, its pArr[0] 
		* ((i + 2) * 10) + (i + 1)  : ((0 + 2) * 10) + (0 + 1) => (2 * 10) + (1) => 20 + 1 = 21
		* Thus, in the 1st iteration, we are setting the 1st position (index 0) element of the array pArr to 21
		*
		* Iteration2: i=1 starting position 
		* *(pArr + i)               : Second array element (index 1), in other words, pArr[1] 
		* ((i + 2) * 10) + (i + 1)  : ((1 + 2) * 10) + (1 + 1) => (3 * 10) + (1) => 30 + 2 = 32
		* Thus, in the 2nd iteration, we are setting the 2nd position (index 1) element of the array pArr to 32
		*
		* This continues until we reach game 5 (iteration 5)
		* 
		* Iteration5: i=4 starting position 
		* *(pArr + i)               : fifth array element (index 4), in other words, pArr[4] 
		* ((i + 2) * 10) + (i + 1)  : ((4 + 2) * 10) + (4 + 1) => (6 * 10) + (5) => 60 + 5 = 65
		* Thus, in the 5th iteration, we are setting the 5th position (index 4) element of the array pArr to 65
	    */
	}
	
	int gameNumber;
	//cout << "Enter game# from 1-5 to check: ";
	cin >> gameNumber;
	
	alterGameScore(pArr, gameNumber - 1); // Add additional points to the game#'s existing score
	
	//cout << "New value: ";
	cout << *(pArr + gameNumber - 1) << endl; // Updated score of the game with additional points added now
	
	printScores(pArr, gameMAX); // Print the remaining scores of the other games determined by the given formula, including the game score that was updated/altered
	
	//cout << "\nTotal Score: ";
	cout << endl << totalScore(pArr, gameMAX); // Add up all the scores of games 1-5

}
