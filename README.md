# vigenere-cipher-breaker
This program is an OOP implementation of the Vigenere cipher and Vigenere breaker using letter frequency analysis

### This project includes:
- Encrypting strings with Caesar and Vigenere ciphers
- Frequency analysis to break the encrypted message
- Breaking Vigenere with both known and unknown key length

### This project doesn't include (yet):
- Encryption of characters other than letters
- Brute force approach to break Caesar
- Short word identification technique for Vigenere
- Lanuages other than English

The last two things are considered to be added in the near future.  
The overall functionality of the 4 Java classes are described below.


## 1. Caesar Cipher

Caesar cipher is a shift cipher, one of the simplest techniques in cryptography. The key is the shift distance, and it is the same for every letter.

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Caesar_cipher_left_shift_of_3.svg/1200px-Caesar_cipher_left_shift_of_3.svg.png" width="250">

Some variations include other characters to the alphabet, such as '(', ';', '&', to encrypt them as well.


## 2. Caesar Breaker

There are two main methods to break Caesar:

- Brute force

This requires maximum 25 iterations over possible keys for English text, since there are 26 letters.

- Letter frequency analysis

This technique has been described in Edgar Allan Poe's "The Gold-Bug".

It is based on the fact that in most languages, there is one letter that occurs significantly more often than others. In English, this is 'e'.
Caesar doesn't change values of frequency: it only changes the letter itself. In other words, it is likely that 'e' is hiding under the most common letter in an encrypted message, which leads to the key.

Frequency analysis might not work for every text, especially short ones.


## 3. Vigenere Cipher

Vigenere cipher is based on Caesar. It encrypts each kth letter with its key; the key consists of k numbers.

For example, the message:

<div align="center">"Meet me tonight in the same place"</div>

is encrypted with key [1,2] as:

<div align="center">"Ngfv of uqokhju jp vig ubof qnbef"</div>


Notice that the first 'e' in 'meet' is replaced by 'g' (key=1), while the subsequent 'e' is replaced by 'f' (key=2). The next letter 't' is replaced by 'u'. (again, key=1)


## 4. Vigenere Breaker

There are two cases to be considered:

1. The key length is known

Here, the encrypted message can be broken into slices consisting of letters with the same key number. Then, frequency analysis is applied to each slice in the same way as Caesar breaker does.

2. The key length is unknown

In this project, every number under the half key length is tried as a key length. Its validity is checked by counting the number of actual words obtained from decrypting with the selected keys using dictionary.


Another technique is the identification of short words, such as 'a', 'I', 'is', 'to', etc.
