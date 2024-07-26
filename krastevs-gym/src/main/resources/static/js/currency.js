let currencyDropDown = document.getElementById('currency');
currencyDropDown.addEventListener('change', currencyChanged);
let priceSpan = document.getElementById('price');
priceSpan.textContent = priceSpan.textContent + ' ' + currencyDropDown.value;

function currencyChanged() {
    let selectedCurrency = currencyDropDown.value;
    let amountInEUR = document.getElementById('priceInEUR').value;

    fetch('/api/convert?' + new URLSearchParams(
        {
            from: 'EUR',
            to: selectedCurrency,
            amount: amountInEUR,
        }
    )).then(response => response.json())
        .then(data => {
            priceSpan.textContent = data.result.toFixed(2) + ' ' + selectedCurrency;
        })
        .catch(error => {
                console.error('An error occurred:', error);
                priceSpan.textContent = 'Error';
            }
        );
}