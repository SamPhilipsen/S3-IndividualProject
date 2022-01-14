beforeEach(() => {
    cy.visit("http://localhost:3000")
    cy.get('.username-input').type('Peter')
    cy.get('.password-input').type('123')
    cy.get('#loginButton').click()
    cy.url().should('include', '/menu')
    cy.visit("http://localhost:3000/menu")
})

it('Input bet to cointoss', () => {
    cy.get('.bet-input').first().type('10').should('have.value', '10')
})

it('Pick coin side', () => {
    cy.get('.bet-coinside').first().check()
})