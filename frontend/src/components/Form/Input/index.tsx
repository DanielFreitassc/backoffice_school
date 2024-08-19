import {
    Button,
    FormControl,
    FormErrorMessage,
    Input,
    InputGroup,
    InputRightElement,
    Text
} from "@chakra-ui/react";
import { forwardRef, useState } from "react";
import { IInputProps } from "./types";
  

export const FormInput = forwardRef(({label, error, type = 'text', ...props}: IInputProps, ref) => {
    const [show, setShow] = useState(false)

    if(type === 'text'){
        return (
            <FormControl isInvalid={!!error}>
                {label && <Text>{label}</Text>}
                <Input type={type} ref={ref} {...props} />
                <FormErrorMessage>
                    {error && error}
                </FormErrorMessage>
            </FormControl>
        )
    }

    if(type === 'password'){
        return (
            <FormControl isInvalid={!!error}>
                {label && <Text>{label}</Text>}
                <InputGroup>
                    <Input
                        type={show ? "text" : "password"}
                        ref={ref}
                        {...props}
                    />
                    <InputRightElement width="4.5rem" paddingRight="10px">
                        <Button
                        h="1.75rem"
                        size="sm"
                        onClick={() => setShow((prev) => !prev)}
                        >
                        {show ? "Ocultar" : "Mostrar"}
                        </Button>
                    </InputRightElement>
                </InputGroup>
            <FormErrorMessage>{error}</FormErrorMessage>
          </FormControl>
        )
    }

})