<?php

namespace App\Form;

use App\Entity\Entreprise;
use App\Entity\OffreEmploi;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class OffreEmploiType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Type', ChoiceType::class, array(
                'choices' => array(
                    'saisonnier' => 'saisonnier',
                    'de Vacances ' => 'de Vacances ',
                    'solidaire ' => 'solidaire ',
                    'long terme ' => 'long terme',
                ),
            ))
            ->add('Experience')
            ->add('Exigence')
            ->add('Poste')
            ->add('Salaire')
            ->add('entreprise',EntityType::class, [
                'class'=>Entreprise::class,
                'choice_label'=>'nom'

            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => OffreEmploi::class,
        ]);
    }
}
