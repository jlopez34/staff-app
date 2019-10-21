package com.softplan.staff;

import com.softplan.staff.model.Person;
import com.softplan.staff.repository.StaffRepository;
import com.softplan.staff.service.StaffService;
import com.softplan.staff.service.impl.StaffServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StaffApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Mock
    StaffRepository staffRepository;

    @InjectMocks
    private StaffService staffService = new StaffServiceImpl();


    @Before
    public void init() {
        Person mockPerson = new Person();
        mockPerson.setNome("Jaime");
        mockPerson.setSexo("Masculino");
        mockPerson.setEmail("moreno.jlopez@gmail.com");
        mockPerson.setDataNacimento("20-01-1982");
        mockPerson.setNaturalidade("Brasil");
        mockPerson.setNacionalidade("Colombia");
        mockPerson.setCPF("236.752.219-01");
        mockPerson.setId("123");
        when(staffRepository.findById("123")).thenReturn(Optional.of(mockPerson));
    }


    //@WithMockUser(username = "USER")
    @WithMockUser("USER")
    @Test
    public void find_login_ok() throws Exception {

        mockMvc.perform(get("/staff/persons/123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.nome", is("Jaime")));

    }


    public static String idPessoa = null;

    @Test
    public void testCadastrarPessoa() {
        //Create sample person
        Person mockPerson = new Person();
        mockPerson.setNome("Jaime");
        mockPerson.setSexo("Masculino");
        mockPerson.setEmail("moreno.jlopez@gmail.com");
        mockPerson.setDataNacimento("20-01-1982");
        mockPerson.setNaturalidade("Brasil");
        mockPerson.setNacionalidade("Colombia");
        mockPerson.setCPF("236.752.219-01");

        //Call Service Cadastrar
        Person personResult = staffService.savePerson(mockPerson);

        //Assert expected result
        Assert.assertNull(personResult);
    }

    @Test
    public void testCadastrarPessoaNOK() {
        //Create sample person
        Person mockPerson = new Person();
        mockPerson.setNome("Jaime");
        mockPerson.setSexo("Masculino");
        mockPerson.setEmail("moreno.jlopez@gmail.com");
        mockPerson.setDataNacimento("20-01-1982");
        mockPerson.setNaturalidade("Brasil");
        mockPerson.setNacionalidade("Colombia");
        mockPerson.setCPF("236.752.219-01");

        //Call Service Cadastrar
        Person personResult = staffService.savePerson(mockPerson);

        //Assert expected result
        Assert.assertNull(personResult);
    }

    @Test
    public void testObterPessoaById() {

        //Call Service Cadastrar
        Person personResult = staffService.getPersonById(idPessoa);

        //Assert expected result
        Assert.assertNull(personResult);
    }

    @Test
    public void testObterTodasPessoa() {
        //Call Service Cadastrar
        List<Person> people = staffService.getAllPersons();

        //Assert expected result
        Assert.assertNotNull(people);
        people.forEach(System.out::println);
        idPessoa = people.get(0).getId();
    }


}
