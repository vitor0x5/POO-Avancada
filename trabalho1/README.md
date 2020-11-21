# Aplicando o Princípio da Responsabilidade Única 
________________________

#### Todo desenvolvedor ja se deparou com esse dilema: *como atualizar uma funcionalidade sem quebrar toda a aplicação*?

 Muitas vezes acaba sendo mais fácil refazer todo o código e evitar as dores de cabeça. Mas porque isso acontece? Robert C. Martin (ou Uncle Bob) uma das cabeças por trás do [Manifesto Ágil](https://agilemanifesto.org/iso/ptbr/manifesto.html), apresentou ao mundo os 5 princípios [SOLID](https://www.eduardopires.net.br/2013/04/orientacao-a-objeto-solid/#:~:text=SOLID%20%C3%A9%20um%20acr%C3%B4nimo%20dos,poderiam%20se%20encaixar%20nesta%20palavra.) para programação orientada a objetos. Esse artigo abordará o primeiro deles: *O Princípio da Responsabilidade Única.*
*****************************

#### O que é?
 O Princípio da Responsabilidade Única define que uma classe, módulo ou função deve ter apenas um motivo para mudar. Ou seja, cada "fragmento" de código deve executar uma função específica que quando alterada não interfere no resto da aplicação. Dessa forma o propgramador pode trabalhar em pequenos escopos da aplicação e não precisa conhecer todo o código para fazer pequenos ajustes.
 ***************************

 #### Ok, mas e o código?

 Como todo bom programador, você deve estar ansioso para ver isso na prática. Mas primeiro será apresentado um código que não segue o Princípio da Responsabilidade Única.
 Considere uma aplicação em que barbeiros anunciam seus horários livres para os clientes marcarem.
 > *Obs: os códigos apresentados foram escritos em Typescript mas podem ser fácilmente entendidos por programadores com alguma experiência*

 >Obs: Foi utilizado um vetor agenda ao invés de uma integração com banco de dados para facilitar o entendimento

```
import { uuid } from 'uuidv4'; // biblioteca para gerar identificadores unicos

interface Horario {
  id: string;
  data: Date;
  cliente?: string;
}

export default class Agenda {
  private agenda: Horario[] = [];

  public criarHorarioParaAtendimento(data: Date): void {
    const novoHorario = {
      id: uuid(),
      data,
    };

    this.agenda.push(novoHorario);
  }

  public marcarHorario(data: Date, cliente: string): void {
    // Verificar se o horário existe na agenda
    const horarioIndex = this.agenda.findIndex((horario) => horario.data === data);
    if (horarioIndex) {
      // Verificar se ja está marcado
      if (!this.agenda[horarioIndex].cliente) {
        this.agenda[horarioIndex].cliente = cliente;
      } else {
        console.log('Esse horário ja está marcado');
      }
    } else {
      console.log('Esse horário não foi disponibilizado pela barbearia');
    }
  }

  public desmarcarHorario(data: Date): void {
    // Verificar se o horario está marcado
    const horarioIndex = this.agenda.findIndex((horario) => horario.data);
    if (this.agenda[horarioIndex].cliente) {
      this.agenda[horarioIndex].cliente = undefined;
    }
  }

  public mostrarAgenda(): void {
    console.log(this.agenda);
  }
}
```
Para entender a quebra do Princípio da Responsabilidade Única vamos analisar o que a classe Agenda faz:

1. Funciona como um repositório
2. Manipula os horários
3. Mostra a agenda

Essa classe possuí 3 responsabilidades diferentes, sendo que apenas uma faz parte do seu escopo (Mostrar a agenda). Agora vamos supor que ocorra uma alteração na persistência dos dados da agenda. O programador responsável pela mudança teria que analisar toda a classe de uma vez para implementar a nova lógica. 
Todo programador passou por isso ao menos uma vez na vida e sabe muito bem que a chance de ocorrer erros é muito grande. Uma forma de evitar muitos deles é aplicando o Princípio da Responsabilidade Única.
******************

#### Aplicando o Princípio da Responsabilidade Única
Primeiramente é importante remover toda a lógica relacionada a persistência de dados de dentro da classe criando um repositório.
```
import { uuid } from 'uuidv4';

interface Horario {
  id: string;
  data: Date;
  cliente?: string;
}

class AgendaRepositorio {
  private static agenda: Horario[];

  public static salvarHorario(data: Date, cliente: string | undefined): void {
    if (cliente) { // marcar horario
      const index = this.agenda.findIndex((horario) => horario.data === data);
      this.agenda[index].cliente = cliente;
    } else { // disponibilizar novo horario para atendimento
      const novoHorarioParaAtendimento = {
        id: uuid(),
        data,
        cliente,
      };
      this.agenda.push(novoHorarioParaAtendimento);
    }
  }

  public static procurarHorario(data: Date): Horario {
    const index = this.agenda.findIndex((horario) => horario.data === data);

    return this.agenda[index];
  }
}
```
Pronto, agora toda a lógica relacionada com a persistência está encapsulada nessa classe.
O próximo passo é criar uma classe responsável somente pela manipulação dos horários.
```
import AgendaRepositorio from './AgendaRepositorio';

interface Horario {
  data: Date;
  cliente?: string;
}

export default class HorarioManipulator {
  private horario: Horario;

  constructor(horario: Horario) {
    this.horario = horario;
  }

  public criarHorarioParaAtendimento(): void {
    AgendaRepositorio.salvarHorario(this.horario.data, undefined);
  }

  public marcarHorario(): void {
    // Verificar se o horário existe na agenda
    const carregarHorario = AgendaRepositorio.procurarHorario(this.horario.data);
    if (carregarHorario) {
      // Verificar se ja está marcado
      if (carregarHorario.cliente) {
        AgendaRepositorio.salvarHorario(this.horario.data, this.horario.cliente);
      } else {
        console.log('Esse horário ja está marcado');
      }
    } else {
      console.log('Esse horário não foi disponibilizado pela barbearia');
    }
  }

  public desmarcarHorario(): void {
    // Verificar se o horario está marcado
    const carregarHorario = AgendaRepositorio.procurarHorario(this.horario.data);
    if (carregarHorario.cliente) {
      AgendaRepositorio.salvarHorario(this.horario.data, undefined);
    }
  }
}

```
Essa é mais uma classe que obedece o Princípio de Responsabilidade Única. Agora é possível alterar as regras de negócio relacionadas à esse dominio da aplicação sem interferir nos outros.
Por fim, a classe Agenda ficou assim:
```
import AgendaRepositorio from './AgendaRepositorio';

export default class Agenda {
  public mostrarAgenda(): void {
    AgendaRepositorio.mostrarAgenda();
  }
}
```
*******************
#### Conclusão
Programar é mais que cumprir os requisitos da aplicação, é necessário seguir padrões e pensar sempre em possiveis alterações e novos recursos. E o Princípio de Responsabilidade Única é um desses padrões que todo programador deve seguir se quiser construir códigos escaláveis e de fácil compreensão.
