#include <string.h>
#include <stdio.h>

char string1[] =
    "A string\tof ,,tokens\nand some  more tokens";
char string2[] =
    "Another string\n\tparsed at the same time.";
char seps[]   = " ,\t\n";
char *token1,
     *token2,
     //*next_token1,
     *next_token2;

int main( void )
{
    printf( "Tokens:\n" );

    // Establish string and get the first token: 
    token1 = strtok( string1, seps);
    token2 = strtok_s ( string2, seps, &next_token2);

    // While there are tokens in "string1" or "string2"
    while ((token1 != NULL) || (token2 != NULL))
    {
        // Get next token:
        if (token1 != NULL)
        {
            printf( " %s\n", token1 );
            token1 = strtok( NULL, seps);
        }
        if (token2 != NULL)
        {
            printf("        %s\n", token2 );
            token2 = strtok_s (NULL, seps, &next_token2); 
        }
    }
}