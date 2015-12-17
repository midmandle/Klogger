#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	functionA(argv[1]);

	return 0;
}

int functionA(char *argv)
{
	char buf[10];
	strcpy(buf, argv);

	return 0;
}

int unreachable()
{
	printf("GG\n");
	return 0;
}
