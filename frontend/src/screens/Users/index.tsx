import { Box, Button, Flex, useDisclosure } from "@chakra-ui/react";
import { Pencil, PlusCircle } from "@phosphor-icons/react";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useState } from "react";
import { IconButton } from "../../components/IconButton";
import { PopoverDelete } from "../../components/Popover";
import { InfoTable, InfoTableContent } from "../../components/Table";
import { Title } from "../../components/Text/Title";
import { UserService } from "../../services/user";
import { DateFormater } from "../../utils/functions";
import { IUser } from "../../utils/types";
import { ModalCreateUser } from "./utils/ModalCreateUser";
import { ModalUpdateUser } from "./utils/ModalUpdateUser";

export const UserList = () => {
  const userService = new UserService();
  const { isOpen, onClose, onOpen } = useDisclosure();
  const {
    isOpen: updateIsOpen,
    onClose: updateClose,
    onOpen: updateOnOpen,
  } = useDisclosure();
  const [selectedUser, setSelectedUser] = useState<IUser>();

  const {
    data: userList,
    isLoading,
    refetch,
  } = useQuery({
    queryKey: ["users"],
    queryFn: () => userService.getUserList(),
  });

  const mutation = useMutation({
    mutationFn: (id: string) => userService.delete(id),
    onSuccess: () => {
      refetch();
    },
  });

  return (
    <>
      {updateIsOpen && selectedUser && (
        <ModalUpdateUser
          isOpen={updateIsOpen}
          onClose={updateClose}
          onSave={() => refetch()}
          user={selectedUser}
        />
      )}
      {
        <ModalCreateUser
          isOpen={isOpen}
          onClose={onClose}
          onSave={() => refetch()}
        />
      }
      <Flex justify="space-between">
        <Title label="Usuários" />
        <IconButton
          icon={<PlusCircle size={26} />}
          label="Cadastrar"
          onClick={onOpen}
        />
      </Flex>
      {!isLoading && userList && (
        <Box minWidth="100%" pt={10}>
          {userList.length > 0 && (
            <InfoTable
              headProps={[
                { label: "Nome" },
                { label: "Username" },
                { label: "Permissão" },
                { label: "Criação" },
                { label: "Ações" },
              ]}
            >
              {userList.map((user) => (
                <InfoTableContent
                  key={user.id}
                  colsBody={[
                    { ceil: user.name },
                    { ceil: user.username },
                    { ceil: user.role },
                    { ceil: DateFormater(user.createdAt) },
                    {
                      ceil: (
                        <Flex>
                          <PopoverDelete
                            section="Usuário"
                            onClick={() => mutation.mutate(user.username)}
                          />
                          <Button
                            variant="none"
                            onClick={() => {
                              setSelectedUser(user);
                              updateOnOpen();
                            }}
                          >
                            <Pencil size={22} />
                          </Button>
                        </Flex>
                      ),
                    },
                  ]}
                />
              ))}
            </InfoTable>
          )}
        </Box>
      )}
    </>
  );
};
