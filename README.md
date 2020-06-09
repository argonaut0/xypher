# Xypher

## A program for encoding/decoding text with various ciphers (Originally an assignment for CPSC 210)

Cryptography is an integral part of modern computing, and has an interesting and extensive history.
This tool automates the use of common *pre-computer* cipher algorithms to allow easy exploration 
of their applications. 

This tool could be used by anyone wishing to play around with or learn about simple ciphers.

Features:
 - Converts to and from common pre-computer ciphers.
 - Command-line interface

This topic is of interest to me because of the role that modern ciphers (encryption) play in
digital security today, and because of the mathematics that are involved.

## Version 1.0.0-alpha features:
- Command line interface
- Atbash Cipher
- Caesar Cipher
- ROT13 Cipher
- Add Ciphers to a sequence
- Encode and decode alphabetic text


## User Stories

### Phase 1:
I want to be able to:
- Select from a list of cipher algorithms.
- Configure settings for each algorithm (if available).
- Use cipher algorithms to convert back and forth from legible text.
- Add cipher algorithms to a sequence to be applied to text sequentially.

### Phase 2:
I want to be able to:
- Save my configuration of ciphers to a file.
- Save my configuration of sequences to a file.
- Quickly load both from a file.

### Phase 3:
I want to be able to:
- Do the above, with a gui
- Hear a nice dopamine-inducing sound when encoding text

## Instructions for Grader
- Run JfxMain
- Choose AtbashCipher from Available encoders
- Click add
- Choose Sequence from Available encoders
- Fill in a fun name
- Click add
- Choose the sequence you just added to CONFIGURE
- Choose AtbashCipher as the active encoder
- (first event)
- Click Add Active Cipher to Sequence!
- (second event)
- Type index 0 and click Remove Cipher from Sequence to remove!
- Select AtbashCipher as the Active Encoder
- type some text into the input
- (Sound event)
- Click encode! and enjoy the nice pleasant sfx while your message is encoded
- (save event)
- Select any Encoder to be active and press save to save it if it isnt already.
- (try Rot13, atbash is already saved)
- (load event)
- Type "TestSequence" into the Name to Load... and click load. check that it is loaded

Notes: Invalid inputs will result in no operations performed. Exceptions are handled by
the javafx library, so the overall application is robust.


### Phase 4: Task 2:
Type Hierarchy:
- Encoder interface represents anything that can be used to encode text.
- Encoder specifies two methods, String -> String encode() and String -> String decode().
- Cipher implements Encoder, is an abstract representation of a Cipher.
- Cipher's implementation of encode/decode calls abstract encodeLetter() and abstract decodeLetter() on each letter in the
string
- Each class that extends Cipher (ciphers package) simply overrides encodeLetter() and decodeLetter() in different ways
to transform text. eg CaesarCipher shifts the letter by a certain number
- The other subclass of Encoder is CipherSequence
- CipherSequence stores a sequence of Ciphers and its implementation of encode() and decode() recursively calls encode()
and decode() in all the Ciphers stored, encoding/decoding the text sequentially.

### Phase 4: Task 3:
- Reduced code duplication by merging saveCipher() and saveSequence() actions into general saveEncoder() action.
- Reduced code duplication by merging loadCipher() and loadSequence() actions into general loadEncoder() action.
- Cleaned up some typos and extraneous code.

- Also, a lot of refactoring was done before this phase/ in between phases:
- Each Cipher used to have it's own encode()/decode() methods, but they all used a lot of iterating through strings.
- ^^ This caused a lot of coupling and code duplication
- This was abstracted away into the Cipher abstract class as a fold function and now all each cipher subclass provides
is encodeLetter() and decodeLetter(), which are passed into a transform() method (that iterates through strings)
as a lambda by encode()/decode().
- Relevant commits are: 9fcec1b + f09c997, 21afaf7, 87c4b86, fd3517f, 5157764

## References
 (To be formatted)
 - https://www.tutorialspoint.com/java/java_documentation.htm
 - http://practicalcryptography.com/ciphers/
 - http://rumkin.com/tools/cipher/
 - https://brilliant.org/wiki/enigma-machine/
 - https://wiki.c2.com/
 - https://stackoverflow.com/questions/16458564/convert-character-to-ascii-numeric-value-in-java
 - https://www.ascii-code.com/
 - https://stackoverflow.com/questions/6802483/how-to-directly-initialize-a-hashmap-in-a-literal-way
 - https://www.geeksforgeeks.org/reflection-in-java/
 - https://stackoverflow.com/questions/1098117/can-one-do-a-for-each-loop-in-java-in-reverse-order
 - https://docs.oracle.com/javase/tutorial/reflect/class/classNew.html
 - https://www.geeksforgeeks.org/variable-arguments-varargs-in-java/
 - https://stackoverflow.com/questions/3629596/deserializing-an-abstract-class-in-gson
 
 ## Libraries
 - https://github.com/google/guava/
 - https://github.com/google/gson
 - https://code.google.com/archive/p/cliche/
