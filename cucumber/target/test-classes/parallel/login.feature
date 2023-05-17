# language: pt
@steps
  Funcionalidade:  Teste Google

    Esquema do Cenário: Pesquisa no Google
      Dado que estou na página inicial do Google
      Quando eu pesquiso por "<termo de pesquisa>"
      Então eu vejo os resultados da pesquisa

      Exemplos:
        | termo de pesquisa |
        | OpenAI            |
        | Testes automatizados |
        | Sistemas distribuídos |
        | OpenAI            |
        | Testes automatizados |
        | Sistemas distribuídos |
        | OpenAI            |
        | Testes automatizados |
        | Sistemas distribuídos |
        | OpenAI            |
        | Testes automatizados |
        | Sistemas distribuídos |


