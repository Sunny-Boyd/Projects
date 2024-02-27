/*==========================================================

 Title:       Assignment 3 - Numerical Reversal
 Course:      CIS 2252
 Author:      <Sun Young Boyd>
 Date:        <9/18/23>
 Description: Numerical Reversal

 ==========================================================
*/

#include <string>
#include <iostream>
#include <vector>


using namespace std;



int main() {
   
  //write your solution here
  
  int input1;
  
  
  cin >> input1;
  

 vector<int> array(input1);
  

  for (int i = 0; i < input1; i++) {
      cin >> array[i];
  }
  
  
   cout << "\n";
 
 
  for (int i = input1 - 1; i >=0; i--) {
      cout << array[i] << " ";
  }
  
  
                   
  return 0;
}



